package com.kurly.hackfesta.service;

import com.kurly.hackfesta.domain.dto.NoticeDto;
import com.kurly.hackfesta.domain.dto.OrderDto;
import com.kurly.hackfesta.domain.model.Notice;
import com.kurly.hackfesta.domain.model.Order;
import com.kurly.hackfesta.domain.model.OrderStatus;
import com.kurly.hackfesta.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    /*
     * 메인화면
     * */
    public List<OrderDto> driverMain(String email) {
        List<Order> orders = orderRepository.findOrdersByEmail(email);

        List<OrderDto> result = new ArrayList<>();
        for (Order order: orders) {
            OrderDto orderDto = OrderDto.builder()
                    .address(order.getAddress())
                    .comment(order.getComment())
                    .entrancePassword(order.getEntrancePassword())
                    .orderNumber(order.getOrderNumber())
                    .total(order.getTotal()).build();

            result.add(orderDto);
        }

        return result;
    }

    /*
     * 배송 상세정보
     * */
    public OrderDto ordersDetail(Long orderId) {
        Optional<Order> findOrder = orderRepository.findById(orderId);
        if (findOrder.isPresent()) {
            Order order = findOrder.get();

            return OrderDto.builder()
                    .address(order.getAddress())
                    .name(order.getCustomer().getName())
                    .comment(order.getComment())
                    .orderNumber(order.getOrderNumber())
                    .total(order.getTotal()).build();
        }
        return null;
    }

    /*
     * 알림
     * */
    public List<NoticeDto> getNotices(String email) {
        List<Notice> notices = orderRepository.findNoticeByEmail(email);

        List<NoticeDto> result = new ArrayList<>();
        for (Notice notice : notices) {
            NoticeDto noticeDto = NoticeDto.builder()
                    .status(notice.getStatus())
                    .createdAt(notice.getCreateAt())
                    .detail(notice.getDetail())
                    .name(notice.getUser().getName()).build();

            result.add(noticeDto);
        }

        return result;
    }

    /*
     * 배송완료 목록
     * */
    public List<OrderDto> getCompletedOrders(String email) {
        List<Order> orders = orderRepository.findOrdersByEmailWithDate(email, LocalDateTime.now());

        List<OrderDto> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getDelivery().getStatus() == OrderStatus.COMPLETE) {
                OrderDto orderDto = OrderDto.builder()
                        .status(String.valueOf(order.getDelivery().getStatus()))
                        .orderNumber(order.getOrderNumber())
                        .createdAt(order.getDelivery().getCreateAt())
                        .address(order.getAddress())
                        .name(order.getCustomer().getName()).build();
            }
        }
        return result;
    }

    /*
     * 배송완료 처리
     * */
    public OrderDto completeOrder(OrderDto orderDto) {
        orderRepository.updateDeliveryState(String.valueOf(OrderStatus.COMPLETE), orderDto.getImageUrl(), orderDto.getId());

        return orderDto;
    }


    /*
     * 배송완료 상세내역
     * */
    public OrderDto completedOrderDetail(Long orderId) {
        Optional<Order> findOrder = orderRepository.findById(orderId);

        if (findOrder.isPresent()) {
            Order order = findOrder.get();

            return OrderDto.builder()
                    .address(order.getAddress())
                    .name(order.getCustomer().getName())
                    .entrancePassword(order.getEntrancePassword())
                    .orderNumber(order.getOrderNumber())
                    .total(order.getTotal())
                    .message(order.getMessage())
                    .build();
        }
        return null;
     }
}
