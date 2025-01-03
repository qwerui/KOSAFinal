package v0.project.mysite.main.wishlist;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import v0.project.mysite.main.common.entity.WishList;
import v0.project.mysite.main.common.entity.WishListId;

import java.time.LocalDateTime;
import java.util.List;

/*
* 작성자 : 홍제기
* 기능 : 찜 목록 관련 DB 테이블 통신
*/

@RequiredArgsConstructor
@Repository
public class WishListRepositoryImpl implements WishListRepository {

    private final EntityManager em;

    @Override
    public List<WishListDTO> getAllWishListByMemberId(String memberId) {

        String jpql = "SELECT new WishListDTO(a.articleId, a.title, a.price, ai.path) "+
                "FROM WishList w "+
                "JOIN Article a ON w.wishListId.articleId = a.articleId "+
                "JOIN ArticleImage ai ON a.articleId = ai.articleImageId.articleId "+
                "WHERE w.wishListId.memberId = :id AND a.endDate > :now AND ai.articleImageId.imageNumber = 1";

        var query = em.createQuery(jpql, WishListDTO.class);

        query.setParameter("id", memberId);
        query.setParameter("now", LocalDateTime.now());

        return query.getResultList();
    }

    @Override
    public void addWish(String memberId, Long articleId) {

        var wish = new WishList();

        wish.setWishListId(new WishListId(memberId, articleId));

        em.persist(wish);
    }

    @Override
    public void deleteWish(String memberId, Long articleId) {
        em.createQuery("DELETE FROM WishList w WHERE w.wishListId.memberId = :member AND w.wishListId.articleId = :article ")
                .setParameter("member", memberId)
                .setParameter("article", articleId)
                .executeUpdate();
    }

    @Override
    public void deleteAllWish(String memberId) {
        em.createQuery("DELETE FROM WishList w WHERE w.wishListId.memberId = :member")
                .setParameter("member", memberId)
                .executeUpdate();
    }

    @Override
    public boolean checkWish(String memberId, Long articleId) {
        try {
            em.createQuery("SELECT 1 FROM WishList w WHERE w.wishListId.memberId = :member AND w.wishListId.articleId = :article")
                    .setParameter("member", memberId)
                    .setParameter("article", articleId)
                    .getSingleResult();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
