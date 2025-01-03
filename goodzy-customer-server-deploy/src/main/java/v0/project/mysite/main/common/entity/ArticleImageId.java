package v0.project.mysite.main.common.entity;

import jakarta.persistence.*;

@Embeddable
public class ArticleImageId {
    @Column(name = "image_number")
    private Long imageNumber;

    @Column(name = "article_id", nullable = false)
    private Long articleId;
}
