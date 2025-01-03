package v0.project.mysite.main.store;

import org.springframework.web.bind.annotation.RequestParam;
import v0.project.mysite.main.common.entity.Store;
import v0.project.mysite.main.store.DTO.SiteListRes;

import java.util.List;
import java.util.Map;

public interface StoreService {
    List<SiteListRes> getStores(String sellerId);
    Map<String, Object> loadSite(String sub, String siteId);
    void saveSite(Map<String, Object> data);
    void createSite(Map<String, Object> data);
    void publishSite(Map<String, Object> data);
}
