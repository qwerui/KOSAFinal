package v0.project.mysite.main.order;

import v0.project.mysite.main.common.entity.Order;
import v0.project.mysite.main.order.DTO.MonthSumDTO;
import v0.project.mysite.main.order.DTO.OrderProductDTO;

import java.util.List;

public interface OrderService {
    List<Order> getOrdersBySeller(String sellerId);
    List<OrderProductDTO> getProductsBySellerAndOrder(String sellerId, Long orderId);
    void updateDelivery(List<OrderProductDTO> orderProducts);
    List<MonthSumDTO> getResultsOfMonth(String sellerId);
}
