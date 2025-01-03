package v0.project.mysite.main.order;

import v0.project.mysite.main.common.entity.Order;
import v0.project.mysite.main.common.entity.OrderProduct;
import v0.project.mysite.main.order.DTO.OrderDTO;
import v0.project.mysite.main.order.DTO.OrderProductDTO;

import java.util.List;

public interface OrderRepository {
    List<Order> getOrdersByMemberId(String memberId);
    List<OrderProductDTO> getOrderProductsByOrderId(Long orderId);
    Order getOrderByOrderId(long orderId);
    void createOrder(OrderDTO orderDTO);
    void addOrderProduct(List<OrderProductDTO> products);
}
