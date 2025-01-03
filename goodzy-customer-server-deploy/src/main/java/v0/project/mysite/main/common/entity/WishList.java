package v0.project.mysite.main.common.entity;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class WishList {

    @EmbeddedId
    private WishListId wishListId;

}