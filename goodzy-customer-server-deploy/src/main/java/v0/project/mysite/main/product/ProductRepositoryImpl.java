package v0.project.mysite.main.product;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import v0.project.mysite.main.common.entity.Product;
import v0.project.mysite.main.common.entity.QProduct;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Product> getProductsByArticleId(Long articleId) {

        QProduct product = QProduct.product;

        return queryFactory.select(product)
                .from(product)
                .where(product.articleId.eq(articleId))
                .fetch();
    }

    @Override
    public void reduceProductCount(Long productId, Integer count) {
        QProduct product = QProduct.product;

        var found = queryFactory.select(product)
                .from(product)
                .where(product.productId.eq(productId))
                .fetchFirst();

        if(found.getCount() < count) {
            throw new RuntimeException("Not Enough Product Count");
        }

        queryFactory.update(product)
                .set(product.count, found.getCount()-count)
                .where(product.productId.eq(productId))
                .execute();
    }
}
