package com.kurly.hackfesta.domain.model;

import com.kurly.hackfesta.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    private int options; //수량

    private int status; //미배송 신청 여부

    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Product> product; //상품 정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User customer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
}
