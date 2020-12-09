package com.izasoft.jcart.web.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.izasoft.jcart.security.AuthenticatedUser;
import com.izasoft.jcart.service.JCLogger;

public abstract class JCartAdminBaseContoller {

	@Autowired private MessageSource messageSource;
	
	protected final JCLogger logger = JCLogger.getLogger(getClass());
	
	protected abstract String getHeaderTitle();
	
	public String getMessage(String code) {
		return messageSource.getMessage(code, null, null);
	}
	
	public String getMessage(String code, String defaultMsg) {
		return messageSource.getMessage(code, null, defaultMsg, null);
	}
	
	@ModelAttribute("authenticatedUser")
	public AuthenticatedUser authenticatedUser(@AuthenticationPrincipal
			AuthenticatedUser authenticatedUser) {
		return authenticatedUser;
	}
	
	public static AuthenticatedUser getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
		if(principal instanceof AuthenticatedUser ) {
			return (AuthenticatedUser) principal;
		}
		
		return null;
	}
	
	public static boolean isLoggedIn() {
		return getCurrentUser()!=null;
	}
	
}
