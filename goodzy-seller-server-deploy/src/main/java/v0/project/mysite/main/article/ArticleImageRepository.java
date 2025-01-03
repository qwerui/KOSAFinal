package v0.project.mysite.main.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import v0.project.mysite.main.common.entity.ArticleImage;

import java.util.List;

public interface ArticleImageRepository extends JpaRepository<ArticleImage, Long> {
    @Query("SELECT ai.path FROM ArticleImage ai WHERE ai.articleImageId.articleId = :articleId ORDER BY ai.articleImageId.imageNumber ASC")
    String findFirstByArticleId(Long articleId);
    @Query("SELECT ai FROM ArticleImage ai WHERE ai.articleImageId.articleId = :articleId ORDER BY ai.articleImageId.imageNumber ASC")
    List<ArticleImage> findAllByArticleId(Long articleId);
}
