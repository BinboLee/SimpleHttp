package com.example.SimpleHttp;

import com.example.SimpleHttp.entity.User;
import com.example.SimpleHttp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleHttpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleHttpApplication.class, args);
	}


}