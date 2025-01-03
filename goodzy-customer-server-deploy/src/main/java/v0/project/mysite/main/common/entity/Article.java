package v0.project.mysite.main.common.entity;




import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Article {

    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @Column(name = "title")
    private String title;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "ship_cost")
    private int shipCost;

    @Column(name = "store_id", nullable = false)
    private String storeId;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "option_name_1")
    private String optionName1;

    @Column(name = "option_name_2")
    private String optionName2;

    @Column(name = "option_name_3")
    private String optionName3;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "opened")
    private Boolean opened;

}