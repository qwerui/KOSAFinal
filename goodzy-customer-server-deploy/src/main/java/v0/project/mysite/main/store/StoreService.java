package v0.project.mysite.main.store;

import org.springframework.web.bind.annotation.RequestParam;
import v0.project.mysite.main.common.entity.Store;
import v0.project.mysite.main.store.DTO.StoreListRes;

import java.util.List;
import java.util.Map;

public interface StoreService {
    List<StoreListRes> getStores(Long category);
    Map<String, Object> getPage(String sellerId, String storeId, String endpoint);
}
