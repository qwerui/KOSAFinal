package v0.project.mysite.main.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderProductDTO {
    private long orderId;
    private long productId;
    private String productName;
    private int price;
    private int orderCount;
    private String invoice;
    private int deliveryStatus;
    private LocalDateTime arrivedDate;
    private boolean settlement;
}
