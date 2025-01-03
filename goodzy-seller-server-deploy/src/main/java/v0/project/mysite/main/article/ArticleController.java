package v0.project.mysite.main.article;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import v0.project.mysite.main.article.dto.ArticleDetailDTO;
import v0.project.mysite.main.article.dto.ArticleListDTO;
import v0.project.mysite.main.article.dto.CreateArticleReq;
import v0.project.mysite.main.common.entity.Category;

import java.util.List;

@RestController
@RequestMapping("/api/seller/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    // 상품 게시글 등록 (상품 등록)
    @PostMapping
    public ResponseEntity<?> createArticle(@RequestBody CreateArticleReq createArticleReq) {
        // request에는 상품 게시글, 게시글 이미지, 상품에 대한 데이터가 들어있어야 함
        Long articleId = articleService.createArticle(createArticleReq);

        return ResponseEntity.status(HttpStatus.CREATED).body(articleId);
    }

    // 상품 게시글 목록 조회
    @GetMapping
    public List<ArticleListDTO> getArticles(@RequestParam String storeId) {
        return articleService.getArticles(storeId);
    }

    @GetMapping("/detail")
    public ArticleDetailDTO getArticleDetail(@RequestParam Long articleId) {
        return articleService.getArticleDetail(articleId);
    }

    @PutMapping
    public ResponseEntity<?> openAndCloseArticle(@RequestParam Long articleId, @RequestParam Boolean isOpen) {
        articleService.toggleArticle(articleId, isOpen);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category")
    public List<Category> getProductCategories() {
        return articleService.getProductCategories();
    }
}
