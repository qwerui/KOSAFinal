package v0.project.mysite.main.article;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import v0.project.mysite.main.article.dto.ArticleDTO;
import v0.project.mysite.main.article.dto.ArticleListDTO;
import v0.project.mysite.main.common.CategoryDTO;
import v0.project.mysite.main.common.entity.Category;
import v0.project.mysite.main.product.ProductRepository;
import v0.project.mysite.main.wishlist.WishListRepository;
import v0.project.mysite.main.wishlist.WishListService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ProductRepository productRepository;
    private final WishListService wishListService;

    @Override
    public List<ArticleListDTO> getArticlesByStoreId(String storeId, Long category) {
        return articleRepository.getArticlesByStoreId(storeId, category);
    }

    @Override
    public ArticleDTO getArticleDetailByArticleId(Long articleId, String memberId) {
        var article = articleRepository.getArticleDetailByArticleId(articleId);
        var products = productRepository.getProductsByArticleId(articleId);
        var favorite = wishListService.checkWish(memberId, articleId);
        var images = articleRepository.getImagesByArticleId(articleId);

        ArticleDTO dto = new ArticleDTO();

        dto.setId(article.getArticleId());
        dto.setPrice(article.getPrice());
        dto.setDescription(article.getDescription());
        dto.setProducts(products);
        dto.setFavorite(favorite);
        dto.setImages(images);
        dto.setTitle(article.getTitle());
        dto.setShipCost(article.getShipCost());

        List<String> optionNames = new ArrayList<>();
        optionNames.add(article.getOptionName1());
        optionNames.add(article.getOptionName2());
        optionNames.add(article.getOptionName3());
        dto.setOptionNames(optionNames);

        return dto;
    }


    @Override
    public List<Category> getArticleCategories(String storeId) {
        return articleRepository.getAllCategoryByStoreId(storeId);
    }
}
