package v0.project.mysite.main.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class CartId implements Serializable {

    @Column(nullable = false, name = "product_id")
    private Long productId;

    @Column(nullable = false, name = "member_id")
    private String memberId;

}