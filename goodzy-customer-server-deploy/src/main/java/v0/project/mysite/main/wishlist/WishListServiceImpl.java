package v0.project.mysite.main.wishlist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * 작성자 : 홍제기
 * 기능 : 찜 목록 관련 비즈니스 로직
 */
@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {

    private final WishListRepository wishListRepository;

    @Override
    public List<WishListDTO> getAllWishListByMemberId(String memberId) {
        return wishListRepository.getAllWishListByMemberId(memberId);
    }

    @Transactional
    @Override
    public void addWish(String memberId, Long articleId) {
        wishListRepository.addWish(memberId, articleId);
    }

    @Transactional
    @Override
    public void deleteWish(String memberId, Long articleId) {
        wishListRepository.deleteWish(memberId, articleId);
    }

    @Transactional
    @Override
    public void deleteAllWish(String memberId) {
        wishListRepository.deleteAllWish(memberId);
    }

    @Override
    public boolean checkWish(String memberId, Long articleId) {
        return wishListRepository.checkWish(memberId, articleId);
    }
}
