package com.kurly.hackfesta.domain.model;

import com.kurly.hackfesta.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Claim extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    private int type; //클레임 유형

    private String specification; //상세내용

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order; //주문 정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User customer; //고객 정보
}
