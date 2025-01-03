package v0.project.mysite.main.article.dto;

import lombok.Data;
import v0.project.mysite.main.common.entity.Article;

@Data
public class ArticleListDTO {
    Article article;
    String imagePath;
}
