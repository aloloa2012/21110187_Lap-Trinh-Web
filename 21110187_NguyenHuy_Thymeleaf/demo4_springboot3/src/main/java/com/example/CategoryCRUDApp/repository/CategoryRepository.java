package com.example.CategoryCRUDApp.repository;

import com.example.CategoryCRUDApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}