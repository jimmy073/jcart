package com.izasoft.jcart.repositoryService;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izasoft.jcart.domain.Product;
import com.izasoft.jcart.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired ProductRepository productRepository;
	
	public Product findProductById(Integer id) {
		return productRepository.findById(id).orElse(null);
	}
	
}
