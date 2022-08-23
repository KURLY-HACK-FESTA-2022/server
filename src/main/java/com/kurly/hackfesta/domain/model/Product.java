package com.kurly.hackfesta.domain.model;

import com.kurly.hackfesta.domain.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    private Double name; //상품명

    private int price; //단가

    @NotNull
    private String barcode; //상품 바코드

    private String specification; //상세정보

    private String packaging; //포장방법

    private String status; //상태

    private String availableToStock; //재고수량
}
