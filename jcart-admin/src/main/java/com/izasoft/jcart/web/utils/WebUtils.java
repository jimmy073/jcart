package com.izasoft.jcart.web.utils;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {

	public static final String IMAGES_PREFIX = "/products/images/";
	public static final String IMAGES_DIR = "E:/jcart/products/";
	
	public static String getURLWithContextPath(HttpServletRequest request)
	{
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
	}
	
	public static String getURLWithCotextPath(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
