package v0.project.mysite.main.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
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

    @Column(name = "shipCost")
    private int shipCost;

    @Column(name = "order_time")
    private LocalDateTime orderTime;

}