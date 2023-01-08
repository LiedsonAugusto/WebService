package com.springproject.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.springproject.curso.entities.Product;
import com.springproject.curso.repositories.ProductRepository;
import com.springproject.curso.services.exceptions.DataBaseException;
import com.springproject.curso.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> opt = productRepository.findById(id);
		return opt.get();
	}
	
	public Product insert(Product product) {
		return productRepository.save(product);
	}
	
	public void delete(Long id) {
		try {
			productRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
	
	public Product update(Long id, Product product) {
		try {
			Product entity = productRepository.getReferenceById(id);
			updateData(entity, product);
			return productRepository.save(entity);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Product entity, Product product) {
		entity.setName(product.getName());
		entity.setDescription(product.getDescription());
		entity.setImgUrl(product.getImgUrl());
		entity.setPrice(product.getPrice());
	}
}
