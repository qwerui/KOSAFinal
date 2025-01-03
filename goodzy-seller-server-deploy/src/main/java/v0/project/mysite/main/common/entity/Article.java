package v0.project.mysite.main.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
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

    @Column(name = "shipCost")
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

    @Column(name = "opened")
    private Boolean opened;


    @Column(name = "category_id", nullable = false)
    private Long categoryId;


    public Article(String title, LocalDateTime startDate, LocalDateTime endDate, int shipCost, String storeId, String description, int price, String optionName1, String optionName2, String optionName3, Boolean opened, Long categoryId) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.shipCost = shipCost;
        this.storeId = storeId;
        this.description = description;
        this.price = price;
        this.optionName1 = optionName1;
        this.optionName2 = optionName2;
        this.optionName3 = optionName3;
        this.opened = opened;
        this.categoryId = categoryId;
    }

}