package v0.project.mysite.main.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthSumDTO {
    private String month;
    private Long sales;
}
