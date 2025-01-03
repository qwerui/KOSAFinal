package v0.project.mysite.main.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderProductDTO {
    private long orderId;
    private long productId;
    private int price;
    private int orderCount;
    private String invoice;
    private int deliveryStatus;
    private LocalDateTime arrivedDate;
    private Boolean settlement;
    private String image;
}
