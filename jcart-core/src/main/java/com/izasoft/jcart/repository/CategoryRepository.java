package com.izasoft.jcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.izasoft.jcart.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category getByName(String name);
	
}
