package v0.project.mysite.main.article;

import org.springframework.data.domain.Pageable;
import v0.project.mysite.main.article.dto.ArticleListDTO;
import v0.project.mysite.main.common.entity.Article;
import v0.project.mysite.main.common.entity.Category;

import java.util.List;

public interface ArticleRepository {
    List<ArticleListDTO> getArticlesByStoreId(String storeId, Long category);
    Article getArticleDetailByArticleId(Long articleId);

    List<String> getImagesByArticleId(Long articleId);
    List<Category> getAllCategoryByStoreId(String storeId);
}
