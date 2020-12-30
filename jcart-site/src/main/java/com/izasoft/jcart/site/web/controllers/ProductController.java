package com.izasoft.jcart.site.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.izasoft.jcart.common.service.CatalogService;
import com.izasoft.jcart.domain.Product;
import com.izasoft.jcart.site.web.utils.WebUtils;

public class ProductController extends JCartSiteBaseController {

	@Autowired protected CatalogService catalogService;
	
	@Override
	protected String getHeaderTitle() {
		return "Product";
	}
	
	@RequestMapping(value = "/products/images/{productId}", method = RequestMethod.GET)
	public void showProductImage(@PathVariable String productId, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			FileSystemResource file = new FileSystemResource(WebUtils.IMAGES_DIR+productId+".jpg");
			response.setContentType("image/jpg");
			org.apache.commons.io.IOUtils.copy(file.getInputStream(), response.getOutputStream());
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/products/{sku}")
	public String product(@PathVariable String sku, Model model) {
		Product product = catalogService.getProductBySku(sku);
		model.addAttribute("product", product);
		return "product";
	}
	
	@RequestMapping(value = "/products")
	public String searchProducts(@RequestParam(name="q") String query, Model model) {
		List<Product> products = catalogService.searchProducts(query);
		model.addAttribute("products", products);
		return "products";
	}

}
