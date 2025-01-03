package v0.project.mysite.main.store;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import v0.project.mysite.main.common.entity.Store;
import v0.project.mysite.main.store.DTO.SiteListRes;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{

    private final StoreRepository storeRepository;
    private final MongoTemplate mongoTemplate;
    private final StringRedisTemplate redisTemplate;

    private final String siteCollection = "editor-site";

    @Override
    public List<SiteListRes> getStores(String sellerId) {

        Query query = new Query();

        query.fields().include("siteId").include("siteName");
        query.addCriteria(Criteria.where("sub").is(sellerId));

        return mongoTemplate.find(query, SiteListRes.class, siteCollection);
    }

    @Override
    public Map<String, Object> loadSite(String sub, String siteId) {

        Query query = new Query();

        query.addCriteria(Criteria.where("sub").is(sub));
        query.addCriteria(Criteria.where("siteId").is(siteId));


        Map<String, Object> result = mongoTemplate.findOne(query, Map.class, siteCollection);

        if(result == null){
            throw new RuntimeException("Invalid site");
        }

        return result;
    }

    @Override
    public void saveSite(Map<String, Object> data) {

        Query query = new Query();

        query.addCriteria(Criteria.where("sub").is(data.get("sub")));
        query.addCriteria(Criteria.where("siteId").is(data.get("siteId")));

        mongoTemplate.remove(query, siteCollection);
        mongoTemplate.save(data, siteCollection);
    }

    @Override
    public void createSite(Map<String, Object> data) {

        Query query = new Query();

        var siteId = data.get("siteId");

        query.addCriteria(Criteria.where("siteId").is(siteId));

        var found = mongoTemplate.findOne(query, Map.class, siteCollection);

        if(found != null) {
            throw new RuntimeException("Duplicated Site ID");
        }

        mongoTemplate.save(data, siteCollection);
    }

    @Override
    @Transactional
    public void publishSite(Map<String, Object> data) {

        String sub = (String)data.get("sub");
        String siteId = (String)data.get("siteId");
        Boolean isOpen = (Boolean)data.get("isPublish");
        Long categoryId = ((Integer)data.get("categoryId")).longValue();

        boolean rdbOk = openStore(sub, siteId, isOpen, categoryId);

        if(!rdbOk) {
            throw new RuntimeException("Publish transaction failed");
        }

        Query query = new Query();

        query.addCriteria(Criteria.where("sub").is(data.get("sub")));
        query.addCriteria(Criteria.where("siteId").is(data.get("siteId")));

        String publishCollection = "publish-site";
        mongoTemplate.remove(query, publishCollection);
        mongoTemplate.save(data, publishCollection);
        mongoTemplate.remove(query, siteCollection);
        mongoTemplate.save(data, siteCollection);

        ArrayList<String> keys = new ArrayList<>();

        var pages = (List)data.get("pages");

        if(pages == null) {
            throw new RuntimeException("Pages Not Exist");
        }

        for(var page : pages) {
            var endpoint = (String)(((Map)page).get("endpoint"));
            keys.add(sub+endpoint);
        }

        redisTemplate.delete(keys);

    }

    private boolean openStore(String sub, String siteId, Boolean isOpen, Long categoryId) {

        storeRepository.publishStore(sub, siteId, isOpen, categoryId);

        return true;
    }
}
