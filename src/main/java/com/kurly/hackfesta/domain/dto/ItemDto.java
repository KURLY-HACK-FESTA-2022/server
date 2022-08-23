package com.kurly.hackfesta.domain.dto;

import com.kurly.hackfesta.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ItemDto {

    private String name;

    private String grade;

    private String status;

    private List<Product> products;

    private int options;

    private String orderNumber;

    private String phone;

    private String address;

    private String placeToReceive;

    private String entrancePassword;
}
