package v0.project.mysite.main.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Table(name = "carts")
public class Cart {

    @EmbeddedId
    @Getter(AccessLevel.NONE)
    private CartId cartId;

    @Column(name = "count")
    private Integer count;

}