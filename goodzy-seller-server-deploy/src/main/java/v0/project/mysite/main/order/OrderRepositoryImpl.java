package v0.project.mysite.main.order;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import v0.project.mysite.main.common.entity.*;
import v0.project.mysite.main.order.DTO.MonthSumDTO;
import v0.project.mysite.main.order.DTO.OrderProductDTO;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository{

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    @Override
    public List<Order> getOrdersBySeller(String sellerId) {

        QStore store = QStore.store;
        QArticle article = QArticle.article;
        QProduct product = QProduct.product;
        QOrderProduct orderProduct = QOrderProduct.orderProduct;
        QOrder order = QOrder.order;

        return queryFactory.select(order)
                .distinct()
                .from(store)
                .innerJoin(article).on(store.storeId.eq(article.storeId))
                .innerJoin(product).on(article.articleId.eq(product.articleId))
                .innerJoin(orderProduct).on(product.productId.eq(orderProduct.orderProductId.productId))
                .innerJoin(order).on(orderProduct.orderProductId.orderId.eq(order.orderId))
                .where(store.sellerId.eq(sellerId))
                .fetch();
    }

    @Override
    public List<OrderProductDTO> getProductsBySellerAndOrder(String sellerId, Long orderId) {

        QStore store = QStore.store;
        QArticle article = QArticle.article;
        QProduct product = QProduct.product;
        QOrderProduct orderProduct = QOrderProduct.orderProduct;

        return queryFactory.select(Projections.constructor(
                OrderProductDTO.class,
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
                orderProduct.price,
                orderProduct.orderCount,
                orderProduct.invoice,
                orderProduct.deliveryStatus,
                orderProduct.arrivedDate,
                orderProduct.settlement
                        )
                )
                .from(store)
                .innerJoin(article).on(store.storeId.eq(article.storeId))
                .innerJoin(product).on(article.articleId.eq(product.articleId))
                .innerJoin(orderProduct).on(product.productId.eq(orderProduct.orderProductId.productId))
                .where(store.sellerId.eq(sellerId).and(orderProduct.orderProductId.orderId.eq(orderId)))
                .fetch();
    }

    @Override
    public void updateDelivery(List<OrderProductDTO> orderProducts) {
        QOrderProduct orderProduct = QOrderProduct.orderProduct;

        for(int i=0;i<orderProducts.size();i++) {

            var departedProduct = orderProducts.get(i);

            queryFactory.update(orderProduct)
                    .set(orderProduct.invoice, departedProduct.getInvoice())
                    .set(orderProduct.deliveryStatus, 1)
                    .where(orderProduct.orderProductId.orderId.eq(departedProduct.getOrderId())
                            .and(orderProduct.orderProductId.productId.eq(departedProduct.getProductId())))
                    .execute();

            if (i % 50 == 0) {
                em.flush();
                em.clear();
            }

            em.flush();
            em.clear();
        }
    }

    @Override
    public List<MonthSumDTO> getResultsOfMonth(String sellerId) {
        QStore store = QStore.store;
        QArticle article = QArticle.article;
        QProduct product = QProduct.product;
        QOrderProduct orderProduct = QOrderProduct.orderProduct;

        return queryFactory.select(Projections.constructor(
                    MonthSumDTO.class,
                        Expressions.stringTemplate("function('DATE_FORMAT', {0}, '%Y-%m')", orderProduct.arrivedDate),
                        orderProduct.price.multiply(orderProduct.orderCount).sum().longValue()
                ))
                .from(store)
                .innerJoin(article).on(store.storeId.eq(article.storeId))
                .innerJoin(product).on(article.articleId.eq(product.articleId))
                .innerJoin(orderProduct).on(product.productId.eq(orderProduct.orderProductId.productId))
                .where(store.sellerId.eq(sellerId).and(orderProduct.arrivedDate.isNotNull()).and(orderProduct.settlement.eq(true)))
                .groupBy(Expressions.stringTemplate("function('DATE_FORMAT', {0}, '%Y-%m')", orderProduct.arrivedDate))
                .fetch();
    }


}
