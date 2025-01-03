package v0.project.mysite.main.order;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import v0.project.mysite.main.common.entity.*;
import v0.project.mysite.main.order.DTO.OrderDTO;
import v0.project.mysite.main.order.DTO.OrderProductDTO;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository{

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    @Override
    public List<Order> getOrdersByMemberId(String memberId) {
        QOrder order = QOrder.order;

        return queryFactory.select(order)
                .from(order)
                .where(order.userId.eq(memberId))
                .fetch();
    }

    @Override
    public List<OrderProductDTO> getOrderProductsByOrderId(Long orderId) {
        QOrderProduct orderProduct = QOrderProduct.orderProduct;
        QProduct product = QProduct.product;
        QArticle article = QArticle.article;
        QArticleImage articleImage = QArticleImage.articleImage;

        return queryFactory.select(
                Projections.constructor(OrderProductDTO.class,
                        orderProduct.orderProductId.orderId,
                        orderProduct.orderProductId.productId,
                        orderProduct.price,
                        orderProduct.orderCount,
                        orderProduct.invoice,
                        orderProduct.deliveryStatus,
                        orderProduct.arrivedDate,
                        orderProduct.settlement,
                        articleImage.path
                ))
                .from(orderProduct)
                .innerJoin(product).on(orderProduct.orderProductId.productId.eq(product.productId))
                .innerJoin(article).on(product.articleId.eq(article.articleId))
                .innerJoin(articleImage).on(article.articleId.eq(articleImage.articleImageId.articleId))
                .where(orderProduct.orderProductId.orderId.eq(orderId).and(articleImage.articleImageId.imageNumber.eq(1L)))
                .orderBy(orderProduct.orderProductId.productId.asc())
                .fetch();
    }

    @Override
    public Order getOrderByOrderId(long orderId) {
        QOrder order = QOrder.order;
        return queryFactory.select(order)
                .from(order)
                .where(order.orderId.eq(orderId))
                .fetchFirst();
    }

    @Override
    public void createOrder(OrderDTO orderDTO) {
        QOrder order = QOrder.order;

        queryFactory.insert(order)
                .columns(order.orderId,
                        order.impUid,
                        order.userId,
                        order.orderTitle,
                        order.address,
                        order.shipCost,
                        order.orderTime)
                .values(orderDTO.getOrderId(),
                        orderDTO.getImpUid(),
                        orderDTO.getUserId(),
                        orderDTO.getOrderTitle(),
                        orderDTO.getAddress(),
                        orderDTO.getShipCost(),
                        LocalDateTime.now())
                .execute();
    }

    @Override
    public void addOrderProduct(List<OrderProductDTO> products) {

        int batchSize = 50;

        for (int i = 0; i < products.size(); i++) {
            var product = products.get(i);
            var order = OrderProduct.builder()
                    .orderCount(product.getOrderCount())
                    .price(product.getPrice())
                    .settlement(false)
                    .orderProductId(OrderProductId.builder()
                            .orderId(product.getOrderId())
                            .productId(product.getProductId())
                            .build())
                            .build();

            em.persist(order);
            if (i % batchSize == 0 && i > 0) {
                em.flush();  // 데이터베이스에 반영
                em.clear();   // 영속성 컨텍스트 비우기
            }
        }
        // 남은 엔티티 저장
        em.flush();
        em.clear();
    }
}
