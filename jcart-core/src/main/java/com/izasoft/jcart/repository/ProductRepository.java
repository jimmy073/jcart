package com.izasoft.jcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.izasoft.jcart.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByName(String name);
	
	Product findBySku (String sku);
	
	@Query("select p from Product p where p.name like ?1 or p.description like ?1")
	List<Product> search(String query);
	
}
