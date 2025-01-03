package v0.project.mysite.main.article;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import v0.project.mysite.main.article.dto.ArticleDTO;
import v0.project.mysite.main.article.dto.ArticleListDTO;
import v0.project.mysite.main.common.entity.Category;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
@Log
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/products")
    public List<ArticleListDTO> getArticlesByStoreId(@RequestParam String storeId, @RequestParam(required = false, defaultValue = "0") Long category) {
        return articleService.getArticlesByStoreId(storeId, category);
    }

    @GetMapping("/product/detail")
    public ArticleDTO getArticleDetailByArticleId(@RequestParam Long articleId, @RequestParam String memberId) {
        return articleService.getArticleDetailByArticleId(articleId, memberId);
    }

    @GetMapping("/product/category")
    public List<Category> getCategoriesByStoreId(@RequestParam String storeId) {
        return articleService.getArticleCategories(storeId);
    }
}
