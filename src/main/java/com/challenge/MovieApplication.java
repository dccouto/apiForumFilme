package com.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MovieApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/movie/api");
		SpringApplication.run(MovieApplication.class, args);
	}

}
