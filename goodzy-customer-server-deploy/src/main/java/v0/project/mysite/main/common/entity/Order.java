package v0.project.mysite.main.common.entity;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "imp_uid")
    private String impUid; //결제 정보

    @Column(name = "order_title")
    private String orderTitle; //성능을 위한 컬럼(없으면 주문상품도 조회해야함)

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "address")
    private String address;

    @Column(name = "ship_cost")
    private int shipCost;

    @Column(name = "order_time")
    private LocalDateTime orderTime;

}