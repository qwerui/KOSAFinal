package v0.project.mysite.main.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    @Column(name = "store_id")
    private String storeId;

    @Column(name = "path")
    private String path;

    @Column(name = "name")
    private String name;

    @Column(name = "opened")
    private boolean opened;

    @Column(name = "store_image")
    private String storeImage;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "seller_id")
    private String sellerId;
}