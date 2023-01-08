package com.springproject.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.springproject.curso.entities.Category;
import com.springproject.curso.repositories.CategoryRepository;
import com.springproject.curso.services.exceptions.DataBaseException;
import com.springproject.curso.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> obj = categoryRepository.findById(id);
		return obj.get();
	}
	
	public Category insert(Category category) {
		return categoryRepository.save(category);
	}
	
	public void delete(Long id) {
		try {
			categoryRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
	
	public Category update(Long id, Category category) {
		try {
			Category entity = categoryRepository.getReferenceById(id);
			updateData(entity, category);
			return categoryRepository.save(entity);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Category entity, Category category) {
		entity.setName(category.getName());
	}
}
