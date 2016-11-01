package com.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication 							// says this program is a spring boot application
@ComponentScan("com.controller") 				// informs the server to look for controller in the specified package
@EntityScan("com.domain")						// Specifies package to look for Entity files
@EnableJpaRepositories("com.domain.repository")	// Specifies package to look for JPA Repository files
public class Application {

	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("../css/**").addResourceLocations("classpath:../css/*");
    }
}	
