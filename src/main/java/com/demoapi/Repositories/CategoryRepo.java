package com.demoapi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demoapi.models.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
	
}
