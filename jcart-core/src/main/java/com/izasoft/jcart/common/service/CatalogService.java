package com.izasoft.jcart.common.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izasoft.jcart.JCartException;
import com.izasoft.jcart.domain.Category;
import com.izasoft.jcart.domain.Product;
import com.izasoft.jcart.repository.CategoryRepository;
import com.izasoft.jcart.repository.ProductRepository;
import com.izasoft.jcart.repositoryService.CategoryService;
import com.izasoft.jcart.repositoryService.ProductService;

@Service
@Transactional
public class CatalogService {

	@Autowired ProductRepository productRepository;
	@Autowired CategoryRepository categoryRepository;
	@Autowired CategoryService categoryService;
	@Autowired ProductService productService;
	
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public Category getCategoryByName(String name) {
		return categoryRepository.getByName(name);
	}
	
	public Category getCategoryById(Integer id) {
		return categoryService.findCategoryById(id);
	}
	
	public Category createCategory(Category category) {
		Category persistedCategory = getCategoryByName(category.getName());
		
		if(persistedCategory != null) {
			throw new JCartException("Category "+category.getName()+" already exist");
		}
		 
		return categoryRepository.save(category);
	}
	
	public Category updateCategory(Category category) {
		Category persistedCategory = getCategoryById(category.getId());
		if(persistedCategory == null){
			throw new JCartException("Category "+category.getId()+" doesn't exist");
		}
		
		persistedCategory.setDescription(category.getDescription());
		persistedCategory.setDisplayOrder(category.getDisplayOrder());
		persistedCategory.setDisabled(category.isDisabled());
		
		return categoryRepository.save(persistedCategory);
	}
	
	public Product getProductById(Integer id) {
		return productService.findProductById(id);
	}
	
	public Product getProductBySku(String sku) {
		return productRepository.findBySku(sku);
	}
	
	public Product createProduct(Product product) {
		Product persistedProduct = getProductBySku(product.getName());
		if(persistedProduct != null){
			throw new JCartException("Product SKU "+product.getSku()+" already exist");
		}
		return productRepository.save(product);
	}
	
	public Product updateProduct(Product product) {
		Product persistedProduct = getProductById(product.getId());
		if(persistedProduct == null){
			throw new JCartException("Product "+product.getId()+" doesn't exist");
		}
		persistedProduct.setDescription(product.getDescription());
		persistedProduct.setDisabled(product.isDisabled());
		persistedProduct.setPrice(product.getPrice());
		persistedProduct.setCategory(getCategoryById(product.getCategory().getId()));
		return productRepository.save(persistedProduct);
	}

	public List<Product> searchProducts(String query) {
		return productRepository.search("%"+query+"%");
	}
}
	
