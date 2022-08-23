package com.kurly.hackfesta.controller;

import com.kurly.hackfesta.domain.dto.NoticeDto;
import com.kurly.hackfesta.domain.dto.OrderDto;
import com.kurly.hackfesta.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    /*
    * 메인화면
    * */
    @GetMapping("/orders/{id}")
    public ResponseEntity<?> driverMain(@PathVariable("id") String email) {
        List<OrderDto> orderDtos = orderService.driverMain(email);
        return ResponseEntity.ok().body(orderDtos);
    }


    /*
    * 배송 상세정보
    * */
    @GetMapping("/orders/detail/{id}")
    public ResponseEntity<?> ordersDetail(@PathVariable("id") Long orderId) {
        OrderDto orderDto = orderService.ordersDetail(orderId);
        return ResponseEntity.ok().body(orderDto);
    }

    /*
    * 알림
    * */
    @GetMapping("/notices/{id}")
    public ResponseEntity<?> getNotices(@PathVariable("id") String email) {
        List<NoticeDto> notices = orderService.getNotices(email);
        return ResponseEntity.ok().body(notices);
    }

    /*
    * 배송완료 목록
    * */
    @GetMapping("/orders/complete/{id}")
    public ResponseEntity<?> getCompletedOrders(@PathVariable("id") String email) {
        List<OrderDto> completedOrders = orderService.getCompletedOrders(email);
        return ResponseEntity.ok().body(completedOrders);
    }

    /*
    * 배송완료 처리
    * */
    @PostMapping("/complete")
    public ResponseEntity<?> completeOrder(@RequestParam OrderDto orderDto) {
        OrderDto responseOrderDto = orderService.completeOrder(orderDto);
        return ResponseEntity.ok().body(responseOrderDto);
    }

    /*
    * 배송완료 상세내역
    * */
    @GetMapping("/orders/complete/detail/{id}")
    public ResponseEntity<?> completedOrderDetail(@PathVariable("id") Long orderId) {
        OrderDto orderDto = orderService.completedOrderDetail(orderId);
        return ResponseEntity.ok().body(orderDto);
    }
}
