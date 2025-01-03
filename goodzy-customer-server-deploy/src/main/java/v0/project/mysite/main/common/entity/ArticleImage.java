package v0.project.mysite.main.common.entity;


import jakarta.persistence.*;

@Entity
public class ArticleImage {

    @EmbeddedId
    private ArticleImageId articleImageId;

    @Column(name = "path")
    private String path;
}
