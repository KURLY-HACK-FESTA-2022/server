package com.kurly.hackfesta.domain.dto;

import com.kurly.hackfesta.domain.model.Delivery;
import com.kurly.hackfesta.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderDto {

    private Long id;

    private Double total; //총가격

    private String status; //배송 상태

    private String address; //배송지

    private String comment; //배송 메세지

    private String orderNumber; //운송장 번호

    private String placeToReceive; //받을 장소

    private String entrancePassword; //공동현관 비밀번호

    private String name; //수령인 이름

    private LocalDateTime createdAt; //발생 일자

    private String message; //고객 요청 메세지

    private String imageUrl;
}
