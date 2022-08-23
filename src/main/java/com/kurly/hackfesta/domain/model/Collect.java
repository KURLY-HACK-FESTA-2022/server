package com.kurly.hackfesta.domain.model;

import com.kurly.hackfesta.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Collect extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    private int message; //수거 요청할

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order; //주문 정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private User driver; //수거할 배송기사
}
