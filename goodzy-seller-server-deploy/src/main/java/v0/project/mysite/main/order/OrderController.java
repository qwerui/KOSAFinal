package v0.project.mysite.main.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import v0.project.mysite.main.common.entity.Order;
import v0.project.mysite.main.order.DTO.MonthSumDTO;
import v0.project.mysite.main.order.DTO.OrderProductDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<Order> getOrdersBySeller(@RequestParam String sellerId) {
        return orderService.getOrdersBySeller(sellerId);
    }

    @GetMapping("/product")
    public List<OrderProductDTO> getProductsBySellerAndOrder(String sellerId, Long orderId) {
        return orderService.getProductsBySellerAndOrder(sellerId, orderId);
    }

    @PutMapping
    public ResponseEntity<?> updateDelivery(@RequestBody List<OrderProductDTO> req) {
        orderService.updateDelivery(req);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/result/price")
    public List<MonthSumDTO> getResultsOfMonth(@RequestParam String sellerId) {
        return orderService.getResultsOfMonth(sellerId);
    }
}
