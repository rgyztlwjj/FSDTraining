package com.emart.sellerms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class UploadConfiguration extends WebMvcConfigurationSupport {
	
	@Value("${img.upload-path}")
	private String uploadPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		
		registry.addResourceHandler("/seller/picture/**").addResourceLocations("file:" + uploadPath);
	}

}
