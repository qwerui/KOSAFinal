package v0.project.mysite.main.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ArticleImage {

    @EmbeddedId
    private ArticleImageId articleImageId;

    @Column(name = "path")
    private String path;

}
