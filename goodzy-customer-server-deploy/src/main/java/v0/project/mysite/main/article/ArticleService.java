package v0.project.mysite.main.article;

import v0.project.mysite.main.article.dto.ArticleDTO;
import v0.project.mysite.main.article.dto.ArticleListDTO;
import v0.project.mysite.main.common.CategoryDTO;
import v0.project.mysite.main.common.entity.Category;

import java.util.List;

public interface ArticleService {
    List<ArticleListDTO> getArticlesByStoreId(String storeId, Long category);
    ArticleDTO getArticleDetailByArticleId(Long articleId, String memberId);
    List<Category> getArticleCategories(String storeId);
}
