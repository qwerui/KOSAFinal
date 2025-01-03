package v0.project.mysite.main.article.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import v0.project.mysite.main.common.entity.Product;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

    private long id;
    private String title;
    private boolean favorite;
    private int price;
    private String description;
    private Integer shipCost;

    private List<String> optionNames;
    private List<Product> products;
    private List<String> images;
}
