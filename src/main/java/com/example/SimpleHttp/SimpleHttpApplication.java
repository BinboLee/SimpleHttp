package com.example.SimpleHttp;

import com.example.SimpleHttp.entity.User;
import com.example.SimpleHttp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/*
备注：
	1.本项目实现启动HTTP监听端口35000，用来接收简单的HTTP请求并记录进数据库，过程当中还使用了Redis
	2.项目配置application.porperties在resources下
	3.数据库操作基于JPA，JPA是把对数据库的操作简化为对JAVA对象的操作
	4.整个Springboot项目的结构是，controller负责接收请求，serviece负责处理，repository负责具体数据库的操作，entity是对应表的java类
*/

@SpringBootApplication
public class SimpleHttpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleHttpApplication.class, args);
	}


}

