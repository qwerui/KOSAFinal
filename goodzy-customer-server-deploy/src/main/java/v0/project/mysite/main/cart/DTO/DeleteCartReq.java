package v0.project.mysite.main.cart.DTO;

public record DeleteCartReq(
        String memberId,
        Long productId
) {
}
