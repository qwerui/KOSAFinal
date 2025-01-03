package v0.project.mysite.main.article.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListDTO {
    private long id;
    private String name;
    private String category;
    private int price;
    private String image;
}
