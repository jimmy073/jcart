package com.izasoft.jcart.web.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.izasoft.jcart.domain.Product;
import com.izasoft.jcart.service.CatalogService;

@Component
public class ProductFormValidator implements Validator{
	
	@Autowired protected MessageSource messageSource;
	@Autowired protected CatalogService catalogService;

	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Product product = (Product) target;
		String sku = product.getSku();
		Product p = catalogService.getProductBySku(sku);
		
		if(p != null){
			errors.rejectValue("sku", "error.exists", new Object[]{sku}, "Product SKU "+sku+" already exists");
		}
	}

}
