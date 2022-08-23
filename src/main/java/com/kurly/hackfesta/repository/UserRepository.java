package com.kurly.hackfesta.repository;

import com.kurly.hackfesta.domain.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    Boolean existsByEmail(String email);
    User findByEmailAndPassword(String email, String password);

    @Query("select i from OrderItem i where i.customer.email = :email")
    OrderItem findItemByEmail(String email);

    @Query("select i from OrderItem i where i.id = :id")
    OrderItem findItemById(Long id);
}
