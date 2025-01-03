package v0.project.mysite.main.cart.DTO;

import java.util.List;

public record AddCartReq(
        String memberId,
        List<AddCartItem> items
) {

}
