package v0.project.mysite.main.delivery;

import v0.project.mysite.main.common.entity.OrderProduct;

import java.util.List;

public interface DeliveryRepository {
    void updateDeliveryStatus(long productId, Long orderId);
    List<OrderProduct> getDeliveringProducts();
}
