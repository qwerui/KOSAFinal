package v0.project.mysite.main.wishlist;

import java.util.List;

public interface WishListRepository {
    List<WishListDTO> getAllWishListByMemberId(String memberId);
    void addWish(String memberId, Long articleId);
    void deleteWish(String memberId, Long articleId);
    void deleteAllWish(String memberId);
    boolean checkWish(String memberId, Long articleId);
}
