package v0.project.mysite.main.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import v0.project.mysite.main.cart.DTO.AddCartReq;
import v0.project.mysite.main.cart.DTO.CartDTO;
import v0.project.mysite.main.cart.DTO.DeleteCartReq;
import v0.project.mysite.main.cart.DTO.UpdateCartReq;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer/product/shoppingcart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<CartDTO> getCartByMember(@RequestParam String memberId) {
        return cartService.getCartByMemberId(memberId);
    }

    @PostMapping
    public ResponseEntity<?> addCart(@RequestBody AddCartReq req) {
        cartService.addToCart(req.memberId(), req.items());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<?> updateCart(@RequestBody UpdateCartReq req) {
        cartService.updateCartCount(req.memberId(), req.productId(), req.count());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFromCart(@RequestBody DeleteCartReq req) {
        cartService.deleteFromCart(req.memberId(), req.productId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAllFromCart(@RequestParam String memberId) {
        cartService.deleteAllFromCart(memberId);
        return ResponseEntity.ok().build();
    }
}
