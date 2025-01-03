package v0.project.mysite.main.wishlist;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WishListDTO {

    private Long id;
    private String title;
    private Integer price;
    private String imagePath;

}
