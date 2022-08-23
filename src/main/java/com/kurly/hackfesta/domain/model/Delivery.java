package com.kurly.hackfesta.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kurly.hackfesta.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //배송 상태

    private String imageUrl; //배송완료 이미지

    @OneToOne(mappedBy = "delivery")
    @JsonIgnore
    private Order order; //주문 정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private User driver; //배송기사

    private LocalDateTime completeTime; //배송 완료시간
}
