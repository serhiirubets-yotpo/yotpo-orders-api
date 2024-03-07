package com.example.yotpoordersapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class YotpoOrdersApiApplication {

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to Yotpo Orders API";
	}

	public static void main(String[] args) {
		SpringApplication.run(YotpoOrdersApiApplication.class, args);
	}

}
