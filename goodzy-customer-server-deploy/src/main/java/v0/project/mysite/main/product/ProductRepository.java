package v0.project.mysite.main.product;

import v0.project.mysite.main.common.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getProductsByArticleId(Long articleId);
    void reduceProductCount(Long productId, Integer count);
}
