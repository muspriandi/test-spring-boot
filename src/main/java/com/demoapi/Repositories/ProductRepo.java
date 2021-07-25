package com.demoapi.Repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demoapi.models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {
	
	List<Product> findByProductNameContainsOrderByProductNameAsc(String name);
	
	Page<Product> findByProductNameContains(String name, Pageable pageable);
}
