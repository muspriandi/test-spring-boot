package com.example.demoapi.controllers.manager;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demoapi.Repositories.ProductRepo;
import com.example.demoapi.models.Product;

@Service
public class ProductManager {
	
	@Autowired
	private ProductRepo productRepo;
	
	public List<Product> getAll() {
		return productRepo.findAll();
	}
	
	public Product save(Product product) {
		return productRepo.save(product);
	}
	
	public Product findById(UUID id) {
		
		Optional<Product> product = productRepo.findById(id);
		if (product.isPresent()) {
			return product.get();
		}
		
		return null;
	}
	
	public void remove(UUID id) {
		Product product = findById(id);
		
		if (product != null) {
			productRepo.deleteById(id);
		}
		else {
			throw new RuntimeException("ID PRODUCT :"+ id +" NOT FOUND!");
		}
	}
	
	public List<Product> getProductByName(String name) {
		return productRepo.findByProductNameContainsOrderByProductNameAsc(name);
	}
	
	public Iterable<Product> getProductByNamePaging(String name, Pageable pageable) {
		return productRepo.findByProductNameContains(name, pageable);
	}
}
