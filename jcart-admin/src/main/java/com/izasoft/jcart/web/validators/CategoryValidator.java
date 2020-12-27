package com.izasoft.jcart.web.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.izasoft.jcart.domain.Category;
import com.izasoft.jcart.service.CatalogService;

@Component
public class CategoryValidator implements Validator{

	@Autowired protected CatalogService catalogService;
	@Autowired protected MessageSource messageSource;
	
	public boolean supports(Class<?> clazz) {
		return Category.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Category category = (Category) target;
		String name = category.getName();
		Category categoryByName = catalogService.getCategoryByName(name);
		
		if(categoryByName != null) {
			errors.rejectValue("name", "error.exist", new Object[] {name}, "Category "+ category.getName()+" already exist");
		}
	}

}
