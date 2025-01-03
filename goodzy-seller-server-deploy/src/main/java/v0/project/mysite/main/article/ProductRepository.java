package v0.project.mysite.main.article;

import org.springframework.data.jpa.repository.JpaRepository;
import v0.project.mysite.main.common.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByArticleId(Long articleId);
}
