package com.springproject.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springproject.curso.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
