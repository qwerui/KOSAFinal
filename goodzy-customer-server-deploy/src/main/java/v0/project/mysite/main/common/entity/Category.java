package v0.project.mysite.main.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @Column(name="category_id")
    private Long categoryId;
    @Column(name="name")
    private String name;
    @Column(name="type")
    @Pattern(regexp = "store|product")
    private String type;
}
