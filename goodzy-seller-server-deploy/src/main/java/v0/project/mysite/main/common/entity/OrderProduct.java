package v0.project.mysite.main.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_products")
@Getter
public class OrderProduct {

    @EmbeddedId
    private OrderProductId orderProductId;

    @Column(name = "order_count")
    private int orderCount;

    @Column(name = "price")
    private int price;

    @Column(name = "invoice")
    private String invoice;

    @Column(name = "delivery_status")
    private int deliveryStatus;

    @Column(name = "arrived_date")
    private LocalDateTime arrivedDate;

    @Column(name = "settlement")
    private Boolean settlement;
}