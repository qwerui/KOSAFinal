package v0.project.mysite.main.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import v0.project.mysite.main.order.DTO.OrderDTO;
import v0.project.mysite.main.order.DTO.OrderProductDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/history")
    public List<OrderDTO> getAllOrdersByMemberId(@RequestParam String memberId) {
        return orderService.getOrdersByMemberId(memberId);
    }

    @GetMapping("/number")
    public Long getTodayOrderNumber() {
        return orderService.getTodayOrderNumber();
    }

    @GetMapping("/detail/{orderId}")
    public OrderDTO getOrderDetail(@PathVariable Long orderId) {

        return orderService.getOrderByOrderId(orderId);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO req) {

        orderService.createOrder(req);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
