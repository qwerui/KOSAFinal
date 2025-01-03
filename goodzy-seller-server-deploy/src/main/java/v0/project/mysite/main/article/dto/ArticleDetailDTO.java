package v0.project.mysite.main.article.dto;

import lombok.Data;
import v0.project.mysite.main.common.entity.Article;
import v0.project.mysite.main.common.entity.ArticleImage;
import v0.project.mysite.main.common.entity.Product;

import java.util.List;

@Data
public class ArticleDetailDTO {
    Article article;
    List<Product> products;
    List<ArticleImage> images;
}
