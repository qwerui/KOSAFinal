package v0.project.mysite.main.settlement.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SettlementDTO {
    private Long orderId;
    private Long productId;
    private String productName;
    private LocalDateTime settlementDate;
    private Boolean settlement;
    private Integer price;

    public SettlementDTO(Long orderId, Long productId, String productName, LocalDateTime settlementDate, Boolean settlement, Integer price) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.settlementDate = settlementDate.plusDays(7);
        this.settlement = settlement;
        this.price = price;
    }
}
