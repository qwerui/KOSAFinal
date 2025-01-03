package v0.project.mysite.main.delivery;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import v0.project.mysite.main.common.entity.OrderProduct;
import v0.project.mysite.main.common.entity.QOrderProduct;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DeliveryRepositoryImpl implements DeliveryRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    @Transactional
    public void updateDeliveryStatus(long productId, Long orderId) {
        QOrderProduct orderProduct = QOrderProduct.orderProduct;

        queryFactory.update(orderProduct)
                .set(orderProduct.deliveryStatus, 2)
                .set(orderProduct.arrivedDate, LocalDateTime.now())
                .where(orderProduct.orderProductId.orderId.eq(orderId).and(orderProduct.orderProductId.productId.eq(productId)))
                .execute();
    }

    @Override
    public List<OrderProduct> getDeliveringProducts() {
        QOrderProduct orderProduct = QOrderProduct.orderProduct;

        return queryFactory.select(orderProduct)
                .from(orderProduct)
                .where(orderProduct.deliveryStatus.eq(1))
                .fetch();
    }
}
