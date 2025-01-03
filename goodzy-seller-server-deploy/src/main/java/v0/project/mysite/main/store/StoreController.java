package v0.project.mysite.main.store;

import com.amazonaws.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import v0.project.mysite.main.common.entity.Store;
import v0.project.mysite.main.store.DTO.CreateStoreReq;
import v0.project.mysite.main.store.DTO.SiteListRes;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller")
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/dashboard/sites")
    public List<SiteListRes> getStores(@RequestParam String sub) {
        return storeService.getStores(sub);
    }

    @GetMapping("editor/site/load-site")
    public Map<String, Object> loadSite(@RequestParam String sub, @RequestParam String siteId) {
        return storeService.loadSite(sub, siteId);
    }

    @PostMapping("editor/site/save-site")
    public ResponseEntity<?> saveSite(@RequestBody Map<String, Object> data) {
        storeService.saveSite(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("editor/site/create-site")
    public ResponseEntity<?> createSite(@RequestBody Map<String, Object> data) {
        try {
            storeService.createSite(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("중복된 ID입니다.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/site/publish")
    public ResponseEntity<?> publishSite(@RequestBody Map<String, Object> data) {

        storeService.publishSite(data);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
