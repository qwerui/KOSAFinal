package v0.project.mysite.main.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import v0.project.mysite.main.cart.DTO.AddCartItem;
import v0.project.mysite.main.cart.DTO.CartDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public List<CartDTO> getCartByMemberId(String memberId) {
        return cartRepository.getCartByMemberId(memberId);
    }

    @Override
    @Transactional
    public void addToCart(String memberId, List<AddCartItem> products) {
        cartRepository.addToCart(memberId, products);
    }

    @Override
    @Transactional
    public void deleteFromCart(String memberId, Long productId) {
        cartRepository.deleteFromCart(memberId, productId);
    }

    @Override
    @Transactional
    public void deleteAllFromCart(String memberId) {
        cartRepository.deleteAllFromCart(memberId);
    }

    @Override
    @Transactional
    public void updateCartCount(String memberId, Long productId, Integer count) {
        cartRepository.updateCartCount(memberId, productId, count);
    }
}
