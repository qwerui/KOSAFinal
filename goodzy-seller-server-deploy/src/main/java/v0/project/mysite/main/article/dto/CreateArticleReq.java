package v0.project.mysite.main.article.dto;


import java.time.LocalDateTime;
import java.util.List;

public record CreateArticleReq(
        Long articleId,
        String title,
        LocalDateTime startDate,
        LocalDateTime endDate,
        int shipCost,
        String storeId,
        String description,
        int price,
        String optionName1,
        String optionName2,
        String optionName3,
        Long categoryId,
        Boolean isPublished,
        List<ProductRecord> products,
        List<String> articleImages // base64로 받는 경우
//        List<MultipartFile> articleImages
) {
}
