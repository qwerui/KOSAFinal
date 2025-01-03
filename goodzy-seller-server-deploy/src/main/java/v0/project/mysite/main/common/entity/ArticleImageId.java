package v0.project.mysite.main.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ArticleImageId {
    @Column(name = "image_number")
    private Long imageNumber;

    @Column(name = "article_id", nullable = false)
    private Long articleId;
}
