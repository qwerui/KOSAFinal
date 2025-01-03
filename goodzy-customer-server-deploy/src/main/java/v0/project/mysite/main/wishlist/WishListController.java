package v0.project.mysite.main.wishlist;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer/product/wishlist")
public class WishListController {

    private final WishListService wishListService;

    @GetMapping
    public List<WishListDTO> getWishListByMemberId(@RequestParam String memberId) {

        return wishListService.getAllWishListByMemberId(memberId);
    }

    @PostMapping
    public ResponseEntity<?> addWish(@RequestParam String memberId, @RequestParam Long articleId) {
        wishListService.addWish(memberId, articleId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteWish(@RequestParam String memberId, @RequestParam Long articleId) {
        wishListService.deleteWish(memberId, articleId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAllWish(@RequestParam String memberId) {
        wishListService.deleteAllWish(memberId);
        return ResponseEntity.ok().build();
    }

}
