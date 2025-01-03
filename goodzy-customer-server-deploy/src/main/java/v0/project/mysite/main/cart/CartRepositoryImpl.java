package v0.project.mysite.main.cart;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import v0.project.mysite.main.cart.DTO.AddCartItem;
import v0.project.mysite.main.cart.DTO.CartDTO;
import v0.project.mysite.main.common.entity.*;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepository{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CartDTO> getCartByMemberId(String memberId) {

        QProduct product = QProduct.product;
        QCart cart = QCart.cart;
        QArticle article = QArticle.article;
        QArticleImage articleImage = QArticleImage.articleImage;



        return queryFactory.select(Projections.constructor(
                CartDTO.class,
                product,
                cart.count,
                articleImage.path,
                article.title,
                article.articleId,
                article.price,
                article.shipCost
        ))
                .from(cart)
                .innerJoin(product).on(cart.cartId.productId.eq(product.productId))
                .innerJoin(article).on(product.articleId.eq(article.articleId))
                .innerJoin(articleImage).on(article.articleId.eq(articleImage.articleImageId.articleId))
                .where(cart.cartId.memberId.eq(memberId).and(articleImage.articleImageId.imageNumber.eq(1L)))
                .fetch();
    }

    @Override
    public void addToCart(String memberId, List<AddCartItem> products) {
        for(var product : products){
            var cartItem = new Cart();
            cartItem.setCartId(new CartId(product.productId(), memberId));
            cartItem.setCount(product.count());
            em.persist(cartItem);
        }
    }

    @Override
    public void deleteFromCart(String memberId, Long productId) {

        em.createQuery("DELETE FROM Cart c WHERE c.cartId.memberId = :member AND c.cartId.productId = :product")
                .setParameter("member", memberId)
                .setParameter("product", productId)
                .executeUpdate();

    }

    @Override
    public void deleteAllFromCart(String memberId) {
        em.createQuery("DELETE FROM Cart c WHERE c.cartId.memberId = :member")
                .setParameter("member", memberId)
                .executeUpdate();
    }

    @Override
    public void updateCartCount(String memberId, Long productId, Integer count) {
        em.createQuery("UPDATE Cart c SET c.count = :count WHERE c.cartId.memberId = :member AND c.cartId.productId = :product")
                .setParameter("member", memberId)
                .setParameter("product", productId)
                .setParameter("count", count)
                .executeUpdate();
    }
}
