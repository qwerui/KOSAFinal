package v0.project.mysite.main.common.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
@Builder
public class OrderProductId implements Serializable {

    @Column(nullable = false, name = "product_id")
    private Long productId;

    @Column(nullable = false, name = "order_id")
    private Long orderId;

}
