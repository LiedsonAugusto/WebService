package com.springproject.curso.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.springproject.curso.entities.Order;
import com.springproject.curso.entities.Payment;
import com.springproject.curso.entities.enums.OrderStatus;
import com.springproject.curso.repositories.OrderRepository;
import com.springproject.curso.services.exceptions.DataBaseException;
import com.springproject.curso.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> findAll(){
		return orderRepository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = orderRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Order insert(Order order) {
		return orderRepository.save(order);
	}
	
	public void delete(Long id) {
		try {
			orderRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
	
	public Order update (Long id, Order order) {
		try {
			Order entity = orderRepository.getReferenceById(id);
			updateData(entity, order);
			return orderRepository.save(entity);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Order entity, Order order) {
		entity.setOrderStatus(order.getOrderStatus());
		if (order.getOrderStatus() == OrderStatus.PAID || order.getOrderStatus() == OrderStatus.SHIPPED || order.getOrderStatus() == OrderStatus.DELIVERED) {
			entity.setPayment(new Payment(null, Instant.now(), entity));
		}
	
	}
}
