package v0.project.mysite.main.order.DTO;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private long orderId;
    private String userId;
    private String impUid;
    private String orderTitle;
    private String address;
    private int shipCost;
    private List<OrderProductDTO> products;
}
