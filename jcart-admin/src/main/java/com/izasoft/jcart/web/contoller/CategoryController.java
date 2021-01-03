package com.izasoft.jcart.web.contoller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.izasoft.jcart.common.service.CatalogService;
import com.izasoft.jcart.domain.Category;
import com.izasoft.jcart.web.validators.CategoryValidator;

@Controller
//@Secured(SecurityUtils.MANAGE_CATEGORIES)
public class CategoryController extends JCartAdminBaseContoller {

	private static final  String viewPrefix ="categories/";

	@Override
	protected String getHeaderTitle() {
		return "Manage Categories";
	}
	
	@Autowired CatalogService catalogService;
	@Autowired private CategoryValidator categoryValidator;
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String listCategories(Model model) {
		List<Category> list = catalogService.getAllCategories();
		model.addAttribute("categories",list);
		return viewPrefix+"categories";
	}
	
	@RequestMapping(value = "/categories/new", method = RequestMethod.GET)
	public String createCategoryForm(Model model) {
		Category category = new Category();
		model.addAttribute("category",category);
		return viewPrefix+"create_category";
	}
	
	@RequestMapping(value = "/categories", method= RequestMethod.POST)
	public String createCategory(@Valid @ModelAttribute("categiry") Category category, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		categoryValidator.validate(category, result);
		
		if(result.hasErrors()) {
			model.addAttribute("category",category);
			return viewPrefix+"create_category";
		}
		
		Category persistedCategory = catalogService.createCategory(category);
		logger.debug("Created new category with id : {} and name: {}", persistedCategory.getId(), persistedCategory.getName());
		redirectAttributes.addFlashAttribute("info", "Category Created Successfully");
		return "redirect:/categories";
	}
	
	@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
	public String editCategoryForm(Model model, @PathVariable Integer id) {
		Category category = catalogService.getCategoryById(id);
		model.addAttribute("category", category);
		return "edit_category";
	}
	
	@RequestMapping(value = "/categories/{id}", method = RequestMethod.POST)
	public String updateCategory(Category category, Model model, RedirectAttributes redirectAttributes) {
		Category persistedCategory = catalogService.updateCategory(category);
		logger.debug("Created new category with id : {} and name: {}", persistedCategory.getId(), persistedCategory.getName());
		redirectAttributes.addFlashAttribute("info", "Category Updated Successfully");
		return "redirect:/categories";
	}
	
	

}
