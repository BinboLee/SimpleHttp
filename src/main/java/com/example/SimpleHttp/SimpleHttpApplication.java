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

	// 使用@Bean定义一个CommandLineRunner，它会在应用启动后自动执行
	@Bean
	public CommandLineRunner testUserService(UserService userService) {
		return args -> {
			System.out.println("=== 开始测试 UserService ===");

			// 1. 创建一个新用户
			User newUser = userService.createUser("测试用户", "test@example.com");
			System.out.println("✅ 创建用户成功，ID: " + newUser.getId());

			// 2. 统计用户数
			long count = userService.countUsers();
			System.out.println("📊 当前用户总数: " + count);

			// 3. 查询所有用户
			System.out.println("📋 所有用户列表:");
			userService.getAllUsers().forEach(user ->
					System.out.println("   - ID:" + user.getId() + ", 用户名:" + user.getUsername() + ", 邮箱:" + user.getEmail())
			);
		};
	}
}