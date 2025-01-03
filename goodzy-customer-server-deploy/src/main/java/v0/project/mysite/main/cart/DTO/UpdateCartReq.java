package v0.project.mysite.main.cart.DTO;

public record UpdateCartReq(
        String memberId,
        Long productId,
        Integer count
) {
}
