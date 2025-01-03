package v0.project.mysite.main.common.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @Column(name = "member_id", nullable = false)
    private String memberId;  // email

    @Column(name = "member_role")
    private Integer memberRole;

    @Column(name = "member_name", length = 50)
    private String memberName;

    @Column(name = "member_phone", length = 50)
    private String memberPhone;

    @Column(name = "address_first", length = 500)
    private String addressFirst;

    @Column(name = "address_detail", length = 500)
    private String addressDetail;

    @Column(name = "profile_image")
    private String profileImage;

}