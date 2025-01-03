package v0.project.mysite.main.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import v0.project.mysite.main.common.entity.Article;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByStoreId(String storeId);
    Article findFirstByArticleId(Long articleId);
}
