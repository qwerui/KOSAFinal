package v0.project.mysite.main.order;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import v0.project.mysite.main.common.entity.OrderProduct;
import v0.project.mysite.main.order.DTO.OrderDTO;
import v0.project.mysite.main.order.DTO.OrderProductDTO;
import v0.project.mysite.main.product.ProductRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final StringRedisTemplate redisTemplate;
    private final ProductRepository productRepository;

    @Override
    public List<OrderDTO> getOrdersByMemberId(String memberId) {

        var orders = orderRepository.getOrdersByMemberId(memberId);

        List<OrderDTO> dtos = new ArrayList<>();

        for(var order : orders) {
            OrderDTO dto = new OrderDTO();
            dto.setOrderId(order.getOrderId());
            dto.setAddress(order.getAddress());
            dto.setImpUid(order.getImpUid());
            dto.setShipCost(order.getShipCost());
            dto.setUserId(order.getUserId());
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    public OrderDTO getOrderByOrderId(long orderId) {
        var dto = new OrderDTO();

        var order = orderRepository.getOrderByOrderId(orderId);

        dto.setOrderTitle(order.getOrderTitle());
        dto.setOrderId(orderId);
        dto.setAddress(order.getAddress());
        dto.setImpUid(order.getImpUid());
        dto.setUserId(order.getUserId());
        dto.setShipCost(order.getShipCost());

        var products = orderRepository.getOrderProductsByOrderId(orderId);

        dto.setProducts(products);

        return dto;
    }

    private static OrderProductDTO getOrderProductDTO(OrderProduct product) {
        OrderProductDTO orderProductDTO = new OrderProductDTO();

        orderProductDTO.setOrderCount(product.getOrderCount());
        orderProductDTO.setOrderId(product.getOrderProductId().getOrderId());
        orderProductDTO.setPrice(product.getPrice());
        orderProductDTO.setInvoice(product.getInvoice());
        orderProductDTO.setSettlement(product.getSettlement());
        orderProductDTO.setArrivedDate(product.getArrivedDate());
        orderProductDTO.setDeliveryStatus(product.getDeliveryStatus());
        orderProductDTO.setProductId(product.getOrderProductId().getProductId());
        return orderProductDTO;
    }

    @Override
    @Transactional
    public void createOrder(OrderDTO orderDTO) {

        var now = LocalDateTime.now();

        for(var product : orderDTO.getProducts()) {
            product.setOrderId(orderDTO.getOrderId());
            productRepository.reduceProductCount(product.getProductId(), product.getOrderCount());
        }

        orderRepository.createOrder(orderDTO);
        orderRepository.addOrderProduct(orderDTO.getProducts());
    }

    @Override
    public Long getTodayOrderNumber() {

        String orderCountKey = "order_count";
        Long orderCount = redisTemplate.opsForValue().increment(orderCountKey, 1);

        if(orderCount==null) {
            return -1L;
        }

        // 만약 오늘 첫 주문이면 초기화
        if (orderCount == 1) {
            redisTemplate.opsForValue().set(orderCountKey, "1");
        }
        return orderCount;
    }
}
