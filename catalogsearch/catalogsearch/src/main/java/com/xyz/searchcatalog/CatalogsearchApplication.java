package com.xyz.searchcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CatalogsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogsearchApplication.class, args);
		
	}

}
