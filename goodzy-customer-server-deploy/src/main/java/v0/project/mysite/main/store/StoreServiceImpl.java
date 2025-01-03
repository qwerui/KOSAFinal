package v0.project.mysite.main.store;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import v0.project.mysite.main.common.entity.Store;
import v0.project.mysite.main.store.DTO.StoreListRes;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{

    private final StoreRepository storeRepository;
    private final MongoTemplate mongoTemplate;
    private final StringRedisTemplate redisTemplate;

    @Override
    public List<StoreListRes> getStores(Long category) {

        Query query = new Query();

        query.fields()
                .include("siteId")
                .include("sub")
                .include("siteName")
                .include("pages");

        if(category != 0) {
            query.addCriteria(Criteria.where("categoryId").is(category));
        }

        query.addCriteria(Criteria.where("isPublish").is(true));

        return mongoTemplate.find(query, StoreListRes.class, "publish-site");
    }

    @Override
    public Map<String, Object> getPage(String sellerId, String storeId, String endpoint) {


        var cache = redisTemplate.opsForValue().get(sellerId+endpoint);

        if(cache != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(cache, new TypeReference<Map<String, Object>>() {});
            } catch (Exception e) {
                throw new RuntimeException("JSON Deserialize Error: Redis cache");
            }
        }


        Query query = new Query();

        query
                .addCriteria(Criteria.where("siteId").is(storeId))
                .addCriteria(Criteria.where("sub").is(sellerId));

        var foundSite = mongoTemplate.findOne(query, Map.class, "publish-site");

        if(foundSite == null) {
            throw new RuntimeException("Site Not Found");
        }

        var pages = foundSite.get("pages");

        if(!(pages instanceof List) || ((List<Object>)pages).isEmpty()) {
            throw new RuntimeException("Pages Not Found");
        }

        for(var page : (List<Object>)pages) {
            Map<String, Object> casted = (Map<String, Object>)page;

            if(endpoint.equals((String)casted.get("endpoint"))) {
                cachePageToRedis(sellerId+endpoint, casted);
                return casted;
            }
        }
        throw new RuntimeException("Invalid endpoint");
    }

    private void cachePageToRedis(String key, Map<String, Object> page) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            var json = mapper.writeValueAsString(page);
            redisTemplate.opsForValue().set(key, json);
        } catch (Exception e) {
            throw new RuntimeException("JSON Serialize Error: Redis cache");
        }
    }
}
