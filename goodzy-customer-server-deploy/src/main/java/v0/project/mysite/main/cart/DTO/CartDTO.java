package v0.project.mysite.main.cart.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import v0.project.mysite.main.common.entity.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Product product;
    private Integer count;
    private String imagePath;
    private String title;
    private Long articleId;
    private Integer price;
    private Integer shipCost;
}
