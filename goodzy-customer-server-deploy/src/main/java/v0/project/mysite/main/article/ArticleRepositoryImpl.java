package v0.project.mysite.main.article;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import v0.project.mysite.main.article.dto.ArticleListDTO;
import v0.project.mysite.main.common.entity.*;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ArticleListDTO> getArticlesByStoreId(String storeId, Long categoryId) {

        QArticle article = QArticle.article;
        QArticleImage articleImage = QArticleImage.articleImage;
        QCategory category = QCategory.category;

        var now = LocalDateTime.now();

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(article.storeId.eq(storeId));
        builder.and(articleImage.articleImageId.imageNumber.eq(1L));
        builder.and(category.type.eq("product"));

        if(categoryId != 0) {
            builder.and(category.categoryId.eq(categoryId));
        }

//        builder.and(article.startDate.before(now));
//        builder.and(article.endDate.after(now).or(article.endDate.isNull()));
        builder.and(article.opened.eq(true));

        return queryFactory.select(Projections.constructor(ArticleListDTO.class,
                article.articleId,
                article.title,
                category.name,
                article.price,
                articleImage.path
                ))
                .from(article)
                .innerJoin(category).on(article.categoryId.eq(category.categoryId))
                .innerJoin(articleImage).on(article.articleId.eq(articleImage.articleImageId.articleId))
                .where(builder)
                .fetch();
    }

    @Override
    public Article getArticleDetailByArticleId(Long articleId) {

        QArticle article = QArticle.article;

        return queryFactory.select(article)
                .from(article)
                .where(article.articleId.eq(articleId))
                .fetchFirst();
    }

    @Override
    public List<String> getImagesByArticleId(Long articleId) {

        QArticleImage articleImage = QArticleImage.articleImage;

        return queryFactory.select(articleImage.path)
                .from(articleImage)
                .where(articleImage.articleImageId.articleId.eq(articleId))
                .fetch();
    }

    @Override
    public List<Category> getAllCategoryByStoreId(String storeId) {

        QArticle article = QArticle.article;
        QCategory category = QCategory.category;

        return queryFactory.select(category)
                .distinct()
                .from(article)
                .innerJoin(category).on(category.categoryId.eq(article.categoryId))
                .where(article.storeId.eq(storeId))
                .fetch();
    }
}
