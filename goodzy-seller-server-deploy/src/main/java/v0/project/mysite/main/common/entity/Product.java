package v0.project.mysite.main.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import v0.project.mysite.main.article.dto.ProductRecord;

@Entity
@RequiredArgsConstructor
@Getter
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "article_id", nullable = false)
    private Long articleId;

    @Column(name = "option_1")
    private String option1;

    @Column(name = "option_2")
    private String option2;

    @Column(name = "option_3")
    private String option3;

    @Column(name = "count")
    private int count;

    @Column(name = "cost")
    private int cost;

    public Product(Long articleId, ProductRecord productRecord) {
        this.articleId = articleId;
        this.option1 = productRecord.option1();
        this.option2 = productRecord.option2();
        this.option3 = productRecord.option3();
        this.count = productRecord.count();
        this.cost = productRecord.cost();
    }
}