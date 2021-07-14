package com.example.demoapi.Repositories;


import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoapi.models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, UUID> {
	
	List<Product> findByProductNameContainsOrderByProductNameAsc(String name);
	
	Page<Product> findByProductNameContains(String name, Pageable pageable);
}
