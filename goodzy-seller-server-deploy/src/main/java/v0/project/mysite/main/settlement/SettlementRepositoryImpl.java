package v0.project.mysite.main.settlement;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import v0.project.mysite.main.common.entity.QArticle;
import v0.project.mysite.main.common.entity.QOrderProduct;
import v0.project.mysite.main.common.entity.QProduct;
import v0.project.mysite.main.common.entity.QStore;
import v0.project.mysite.main.settlement.DTO.SettlementDTO;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SettlementRepositoryImpl implements SettlementRepository{

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    @Override
    public List<SettlementDTO> getSettlementBySeller(String sellerId) {

        QStore store = QStore.store;
        QArticle article = QArticle.article;
        QProduct product = QProduct.product;
        QOrderProduct orderProduct = QOrderProduct.orderProduct;

        return queryFactory.select(Projections.constructor(
                    SettlementDTO.class,
                        orderProduct.orderProductId.orderId,
                    orderProduct.orderProductId.productId,
                    article.title.append(
                            new CaseBuilder()
                                    .when(product.option3.isNotNull())
                                    .then(product.option1.prepend("(").append("/").concat(product.option2).append("/").concat(product.option3).append(")"))
                                    .when(product.option2.isNotNull())
                                    .then(product.option1.prepend("(").append("/").concat(product.option2).append(")"))
                                    .when(product.option1.isNotNull())
                                    .then(product.option1.prepend("(").append(")"))
                                    .otherwise("")
                    ),
                    orderProduct.arrivedDate,
                    orderProduct.settlement,
                    orderProduct.price.multiply(orderProduct.orderCount)
                ))
                .from(store)
                .innerJoin(article).on(store.storeId.eq(article.storeId))
                .innerJoin(product).on(article.articleId.eq(product.articleId))
                .innerJoin(orderProduct).on(product.productId.eq(orderProduct.orderProductId.productId))
                .where(store.sellerId.eq(sellerId).and(orderProduct.arrivedDate.isNotNull()))
                .fetch();
    }

    @Override
    public void updateSettlement(List<SettlementDTO> settlements) {
        QOrderProduct orderProduct = QOrderProduct.orderProduct;

        for(int i=0;i<settlements.size();i++) {

            var settlement = settlements.get(i);

            queryFactory.update(orderProduct)
                    .set(orderProduct.settlement, true)
                    .where(orderProduct.orderProductId.orderId.eq(settlement.getOrderId())
                            .and(orderProduct.orderProductId.productId.eq(settlement.getProductId())))
                    .execute();

            if(i % 50 == 0) {
                em.flush();
                em.clear();
            }
        }

        em.flush();
        em.clear();
    }
}
