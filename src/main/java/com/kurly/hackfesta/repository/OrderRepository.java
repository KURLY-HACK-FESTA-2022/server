package com.kurly.hackfesta.repository;

import com.kurly.hackfesta.domain.model.Notice;
import com.kurly.hackfesta.domain.model.Order;
import com.kurly.hackfesta.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.driver.email = :email")
    List<Order> findOrdersByEmail(String email);

    @Query("select n from Notice n where n.user.email = :email")
    List<Notice> findNoticeByEmail(String email);

    @Query("select o from Order o where o.driver.email = :email and o.delivery.createAt = :now")
    List<Order> findOrdersByEmailWithDate(String email, LocalDateTime now);

    @Query("update Delivery d set d.status = :state, d.imageUrl = :imageUrl where d.order.id = :id")
    void updateDeliveryState(String state, String imageUrl, Long id);

}
