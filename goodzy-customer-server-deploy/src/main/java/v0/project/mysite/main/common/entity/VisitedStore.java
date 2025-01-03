package v0.project.mysite.main.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
public class VisitedStore {

    @EmbeddedId
    private VisitedStoreId visitedStoreId;
    @Column(name="visited_time")
    private LocalDateTime visitedTime;
}
