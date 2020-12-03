package com.izasoft.jcart.configuration;


import javax.servlet.Filter;
import javax.servlet.FilterRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import com.izasoft.jcart.security.PostAuthorizationFilter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

	@Autowired 
	private MessageSource messageSource;
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("public/login");
		registry.addRedirectViewController("/", "home");
	}

	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		factory.setValidationMessageSource(messageSource);
		return factory;
	}

	
	@Bean
	public SpringSecurityDialect securityDialect() {
		return new SpringSecurityDialect();
	}
	
	@Autowired 
	private PostAuthorizationFilter postAuthorizationFilter;
	
	@Bean
	public FilterRegistrationBean securityFilterChain(Filter securityFilter) {
		FilterRegistrationBean registration = new FilterRegistrationBean<Filter>(securityFilter);
		registration.setOrder(Integer.MAX_VALUE-1);
		registration.setName(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME);
		return registration;
	}
	
	@Bean
	public FilterRegistrationBean PostAuthorizationFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(postAuthorizationFilter);
		registrationBean.setOrder(Integer.MAX_VALUE);
		return registrationBean;
	}
	
	
}
