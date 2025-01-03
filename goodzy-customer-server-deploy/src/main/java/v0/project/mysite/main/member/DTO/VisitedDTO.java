package v0.project.mysite.main.member.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitedDTO {
    private String storeId;
    private String storeTitle;
    private String storeImage;
}
