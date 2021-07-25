package com.demoapi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demoapi.controllers.json.ProductView;
import com.demoapi.controllers.manager.CategoryManager;
import com.demoapi.controllers.manager.ProductManager;
import com.demoapi.dto.ResponseData;
import com.demoapi.dto.SearchData;
import com.demoapi.models.Category;
import com.demoapi.models.Product;

@RestController
@RequestMapping(path = "/api")
public class ProductController {
	
	@Autowired
	private ProductManager productManager;
	
	@Autowired
	private CategoryManager categoryManager;
	
	@GetMapping(path = "/products")
	public List<Product> getAll() {
		return productManager.getAll();
	}
	
	@PostMapping(path = "/product")
	public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody ProductView productView, Errors errors) {
		
		ResponseData<Product> responseData = new ResponseData<>();
		
		if(errors.hasErrors()) {
			for (ObjectError error : errors.getAllErrors()) {
				responseData.getMessages().add(error.getDefaultMessage());
			}
			responseData.setStatus(false);
			responseData.setPayload(null);
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
		}
		
		Category category = categoryManager.findById(Long.valueOf(productView.getCategoryId()));
		if (category != null) {
			Product product = new Product();
			
			product.setProductName(productView.getProductName());
			product.setProductDescription(productView.getProductDescription());
			product.setProductPrice(productView.getProductPrice());
			product.setCategory(category);
			
			responseData.setStatus(true);
			responseData.setPayload(productManager.save(product));
			
			return ResponseEntity.status(HttpStatus.OK).body(responseData);
		}
		
		responseData.getMessages().add("ID CATEGORY :"+ productView.getCategoryId() +" NOT FOUND!");
		responseData.setStatus(false);
		responseData.setPayload(null);
			
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
	}
	
	@GetMapping(path = "/product/{id}")
	public Product getOne(@PathVariable("id") String id) {
		return productManager.findById(id);
	}
	
	@PutMapping(path = "/product")
	public ResponseEntity<ResponseData<Product>> update(@Valid @RequestBody ProductView productView, Errors errors) {
		
		ResponseData<Product> responseData = new ResponseData<>();
		
		if(errors.hasErrors()) {
			for (ObjectError error : errors.getAllErrors()) {
				responseData.getMessages().add(error.getDefaultMessage());
			}
			responseData.setStatus(false);
			responseData.setPayload(null);
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
		}
		
		Category category = categoryManager.findById(Long.valueOf(productView.getCategoryId()));
		if (category != null) {
			Product product = new Product();
			
			product.setProductId(productView.getProductId());
			product.setProductName(productView.getProductName());
			product.setProductDescription(productView.getProductDescription());
			product.setProductPrice(productView.getProductPrice());
			product.setCategory(category);
			
			responseData.setStatus(true);
			responseData.setPayload(productManager.save(product));
			
			return ResponseEntity.status(HttpStatus.OK).body(responseData);
		}
		
		responseData.getMessages().add("ID CATEGORY :"+ productView.getCategoryId() +" NOT FOUND!");
		responseData.setStatus(false);
		responseData.setPayload(null);
			
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
	}
		
	@DeleteMapping(path = "/product/{id}")
	public void removeOne(@PathVariable("id") String id) {
		productManager.remove(id);
	}
	
	@GetMapping(path = "/products/search")
	public List<Product> getProductByName(@RequestParam("name") String name) {
		return productManager.getProductByName(name);
	}
	
	@PostMapping(path = "/products/search")
	public Iterable<Product> getProductByNamePaging(@RequestBody SearchData searchData) {
		
		Pageable pageable = PageRequest.of(searchData.getPage(), searchData.getSize(), Sort.by("productName"));
		if(searchData.getSort().startsWith("desc")) {
			pageable = PageRequest.of(searchData.getPage(), searchData.getSize(), Sort.by("productName").descending());
		}
		
		return productManager.getProductByNamePaging(searchData.getQuery(), pageable);
	}
}
