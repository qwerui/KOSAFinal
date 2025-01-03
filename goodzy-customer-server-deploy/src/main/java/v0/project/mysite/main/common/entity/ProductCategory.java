package v0.project.mysite.main.common.entity;


import jakarta.persistence.*;

@Entity
public class ProductCategory {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "name")
    private String name;
}