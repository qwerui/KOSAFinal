package v0.project.mysite.main.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store_members")
@NoArgsConstructor
public class StoreMember {

    @EmbeddedId
    @Getter(AccessLevel.NONE)
    private StoreMemberId storeMemberId;

    @Column(name = "verified")
    private boolean verified;

    public StoreMember(StoreMemberId storeMemberId, boolean b) {
        this.storeMemberId = storeMemberId;
        this.verified = b;
    }
}