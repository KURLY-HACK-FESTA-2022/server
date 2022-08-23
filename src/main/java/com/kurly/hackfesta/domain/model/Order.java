package com.kurly.hackfesta.domain.model;

import com.kurly.hackfesta.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    private Double total; //총가격

    private int status; //미배송 여부

    private String address; //배송지

    private String comment; //배송 메세지

    private String orderNumber; //운송장 번호

    private String placeToReceive; //받을 장소

    private String entrancePassword; //공동현관 비밀번호

    private String message; //고객 요청 메세지

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User customer; //주문 고객 -> name

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery; //배송 정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private User driver; //배송기사 정보
}
