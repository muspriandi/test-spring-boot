package com.example.demoapi.controllers.manager;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoapi.Repositories.CategoryRepo;
import com.example.demoapi.models.Category;

@Service
public class CategoryManager {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	public List<Category> getAll() {
		return categoryRepo.findAll();
	}
	
	public Category save(Category category) {
		return categoryRepo.save(category);
	}
	
	public Category findById(Long id) {
		
		Optional<Category> category = categoryRepo.findById(id);
		if (category.isPresent()) {
			return category.get();
		}
		
		return null;
	}
	
	public void remove(Long id) {
		
		Category category = findById(id);
		if (category != null) {
			categoryRepo.deleteById(id);
		}
		else {
			throw new RuntimeException("ID CATEGORY :"+ id +" NOT FOUND!");
		}
	}
}
