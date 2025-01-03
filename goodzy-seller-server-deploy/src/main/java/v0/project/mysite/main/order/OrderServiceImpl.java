package v0.project.mysite.main.order;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import v0.project.mysite.main.common.entity.Order;
import v0.project.mysite.main.delivery.DeliveryManager;
import v0.project.mysite.main.order.DTO.MonthSumDTO;
import v0.project.mysite.main.order.DTO.OrderProductDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DeliveryManager deliveryManager;

    @Override
    public List<Order> getOrdersBySeller(String sellerId) {
        return orderRepository.getOrdersBySeller(sellerId);
    }

    @Override
    public List<OrderProductDTO> getProductsBySellerAndOrder(String sellerId, Long orderId) {
        return orderRepository.getProductsBySellerAndOrder(sellerId, orderId);
    }

    @Override
    @Transactional
    public void updateDelivery(List<OrderProductDTO> orderProducts) {
        orderRepository.updateDelivery(orderProducts);

        for(var orderProduct : orderProducts) {
            deliveryManager.scheduleDeliveryUpdate(orderProduct.getProductId(), orderProduct.getOrderId());
        }
    }

    @Override
    public List<MonthSumDTO> getResultsOfMonth(String sellerId) {
        return orderRepository.getResultsOfMonth(sellerId);
    }
}
