package com.example.demoapi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoapi.controllers.manager.CategoryManager;
import com.example.demoapi.dto.ResponseData;
import com.example.demoapi.models.Category;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {
	
	@Autowired
	private CategoryManager categoryManager;
	
	@GetMapping(path = "/categories")
	public List<Category> getAll() {
		return categoryManager.getAll(); 
	}
	
	@PostMapping(path = "/category")
	public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody Category category, Errors errors) {
		
		ResponseData<Category> responseData = new ResponseData<>();
		if (errors.hasErrors()) {
			for (ObjectError error : errors.getAllErrors()) {
				responseData.getMessages().add(error.getDefaultMessage());
			}
			responseData.setStatus(false);
			responseData.setPayload(null);
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
		}
		
		responseData.setStatus(true);
		responseData.setPayload(categoryManager.save(category));
		
		return ResponseEntity.status(HttpStatus.OK).body(responseData);
	}
	
	@GetMapping(path = "/category/{id}")
	public Category getOne(@PathVariable("id") Long id) {
		return categoryManager.findById(id);
	}
	
	@PutMapping(path = "/category")
	public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody Category category, Errors errors) {
		
		ResponseData<Category> responseData = new ResponseData<>();
		if (errors.hasErrors()) {
			for (ObjectError error : errors.getAllErrors()) {
				responseData.getMessages().add(error.getDefaultMessage());
			}
			responseData.setStatus(false);
			responseData.setPayload(null);
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
		}
		
		responseData.setStatus(true);
		responseData.setPayload(categoryManager.save(category));
		
		return ResponseEntity.status(HttpStatus.OK).body(responseData);
	}
	
	@DeleteMapping(path = "/category/{id}")
	public void removeOne(@PathVariable("id") Long id) {
		categoryManager.remove(id);
	}
}
