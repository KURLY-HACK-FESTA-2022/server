package com.kurly.hackfesta.domain.model;

import com.kurly.hackfesta.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    private String phone; //전화번호

    private String name; //이름

    @Enumerated(EnumType.STRING)
    private Role role; //역할

    private String email; //이메일 -> id로 사용

    private String password; //비밀번호

    private String grade;

    @OneToMany(mappedBy = "driver")
    private List<Delivery> deliveries = new ArrayList<>(); //완료한 배달 목록 -> 배송기사용

    @OneToMany(mappedBy = "user")
    private List<Notice> noticeList = new ArrayList<>(); //알림 목록

    @OneToMany(mappedBy = "driver")
    private List<Order> orderList = new ArrayList<>(); //주문 목록 -> 배송기사용

    @OneToMany(mappedBy = "customer")
    private List<OrderItem> orderItems = new ArrayList<>(); //주문 목록 -> 일반고객용

}
