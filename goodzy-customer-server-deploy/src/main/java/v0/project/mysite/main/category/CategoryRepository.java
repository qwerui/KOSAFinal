package v0.project.mysite.main.category;

import org.springframework.data.jpa.repository.JpaRepository;
import v0.project.mysite.main.common.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByType(String type);
}
