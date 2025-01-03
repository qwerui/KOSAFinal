package v0.project.mysite.main.common.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "store_categories")
public class StoreCategory {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "name")
    private String name;

}
