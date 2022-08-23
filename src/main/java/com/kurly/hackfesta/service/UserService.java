package com.kurly.hackfesta.service;

import com.kurly.hackfesta.domain.dto.ItemDto;
import com.kurly.hackfesta.domain.model.OrderItem;
import com.kurly.hackfesta.domain.model.Product;
import com.kurly.hackfesta.domain.model.User;
import com.kurly.hackfesta.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    /*
     * 로그인
     * */
    public User getByCredentials(String email, String password, PasswordEncoder encoder) {
        User originalUser = userRepository.findByEmail(email);

        if (originalUser != null && encoder.matches(password, originalUser.getPassword())) {
            return originalUser;
        }
        return null;
    }

    /*
     * 메인화면
     * */
    @Transactional(readOnly = true)
    public ItemDto customerMain(String email) {
        OrderItem orderItem = userRepository.findItemByEmail(email);
        User user = userRepository.findByEmail(email);

        ItemDto result = ItemDto.builder()
                .name(user.getName())
                .grade(user.getGrade())
                .status(String.valueOf(orderItem.getOrder().getDelivery().getStatus()))
                .options(orderItem.getOptions()).build();

        for (Product product : orderItem.getProduct()) {
            product.getName();
        }
        result.setProducts(orderItem.getProduct());

        return result;
    }

    /*
     * 주문 상세내용
     * */
    @Transactional(readOnly = true)
    public ItemDto itemsDetail(Long id) {
        OrderItem orderItem = userRepository.findItemById(id);

        for (Product product : orderItem.getProduct()) {
            product.getName();
        }

        return ItemDto.builder()
                .orderNumber(orderItem.getOrder().getOrderNumber())
                .status(String.valueOf(orderItem.getOrder().getDelivery().getStatus()))
                .products(orderItem.getProduct())
                .options(orderItem.getOptions())
                .name(orderItem.getCustomer().getName())
                .phone(orderItem.getCustomer().getPhone())
                .address(orderItem.getOrder().getAddress())
                .placeToReceive(orderItem.getOrder().getPlaceToReceive())
                .entrancePassword(orderItem.getOrder().getEntrancePassword())
                .build();
    }

}
