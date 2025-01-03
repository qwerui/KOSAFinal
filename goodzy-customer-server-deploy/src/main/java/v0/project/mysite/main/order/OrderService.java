package v0.project.mysite.main.order;

import v0.project.mysite.main.order.DTO.OrderDTO;
import v0.project.mysite.main.order.DTO.OrderProductDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getOrdersByMemberId(String memberId);
    OrderDTO getOrderByOrderId(long orderId);
    void createOrder(OrderDTO orderDTO);

    Long getTodayOrderNumber();
}
