package v0.project.mysite.main.common.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
public class WishList {

    @EmbeddedId
    @Getter(AccessLevel.NONE)
    private WishListId wishListId;

}