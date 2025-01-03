package v0.project.mysite.main.article.dto;


public record ProductRecord(
        String option1, // 소분류 (대분류가 size면 여기는 XL ... )
        String option2,
        String option3,
        int count,
        int cost // 추가금
) {
}
