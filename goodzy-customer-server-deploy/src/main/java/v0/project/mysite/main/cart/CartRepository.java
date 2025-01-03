package v0.project.mysite.main.cart;

import v0.project.mysite.main.cart.DTO.AddCartItem;
import v0.project.mysite.main.cart.DTO.CartDTO;

import java.util.List;

public interface CartRepository {
    List<CartDTO> getCartByMemberId(String memberId);
    void addToCart(String memberId, List<AddCartItem> products);
    void deleteFromCart(String memberId, Long productId);
    void deleteAllFromCart(String memberId);
    void updateCartCount(String memberId, Long productId, Integer count);
}
