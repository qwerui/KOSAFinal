package v0.project.mysite.main.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Embeddable
@Getter
@Builder
public class VisitedStoreId {
    @Column(name = "member_id")
    private String memberId;
    @Column(name = "store_id")
    private String storeId;
}
