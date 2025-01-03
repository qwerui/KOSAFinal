package v0.project.mysite.main.store;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import v0.project.mysite.main.common.entity.Store;
import v0.project.mysite.main.store.DTO.StoreListRes;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer/store")
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public List<StoreListRes> getStores(@RequestParam(required = false, defaultValue = "0")Long category) {
        return storeService.getStores(category);
    }

    @GetMapping("/page")
    public Map<String, Object> getPage(@RequestParam String sellerId, @RequestParam String storeId, @RequestParam String endpoint) {
        return storeService.getPage(sellerId, storeId, endpoint);
    }

}
