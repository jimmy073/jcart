package com.izasoft.jcart.repositoryService;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izasoft.jcart.domain.Category;
import com.izasoft.jcart.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {

	@Autowired CategoryRepository categoryRepository;
	
	public Category findCategoryById(Integer id) {
		return categoryRepository.findById(id).orElse(null);
	}
	
}
