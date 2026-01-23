package com.example.SimpleHttp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.annotation.PostConstruct;
import java.sql.*;

@SpringBootApplication
public class SimpleHttpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleHttpApplication.class, args);
	}

	@PostConstruct
	public void connectToDatabase() {
		String url = "jdbc:mysql://localhost:3306/simplehttp";
		String user = "root";
		String password = "Pass001!";

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// 1. 注册JDBC驱动（MySQL 8+自动注册，这步可选）
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. 建立连接
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("✅ 数据库连接成功！");

			// 3. 创建Statement对象
			statement = connection.createStatement();

			// 4. 执行查询
			resultSet = statement.executeQuery("SELECT VERSION()");

			// 5. 处理结果
			if (resultSet.next()) {
				String version = resultSet.getString(1);
				System.out.println("📊 MySQL版本: " + version);
			}

			// 6. 查询当前数据库
			resultSet = statement.executeQuery("SELECT DATABASE()");
			if (resultSet.next()) {
				String dbName = resultSet.getString(1);
				System.out.println("📁 当前数据库: " + dbName);
			}

			// 7. 查询所有表（可选）
			resultSet = statement.executeQuery("SHOW TABLES");
			System.out.println("📋 数据库中的表:");
			boolean hasTables = false;
			while (resultSet.next()) {
				hasTables = true;
				System.out.println("  - " + resultSet.getString(1));
			}
			if (!hasTables) {
				System.out.println("  （暂无表）");
			}

		} catch (ClassNotFoundException e) {
			System.err.println("❌ MySQL驱动未找到！请检查pom.xml中是否添加了mysql-connector-java依赖");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("❌ 数据库连接失败！错误信息: " + e.getMessage());
			System.err.println("   请检查:");
			System.err.println("   1. MySQL服务是否启动");
			System.err.println("   2. 数据库simplehttp是否存在");
			System.err.println("   3. 用户名/密码是否正确");
			System.err.println("   4. 端口3306是否开放");
		} finally {
			// 8. 关闭资源
			try {
				if (resultSet != null) resultSet.close();
				if (statement != null) statement.close();
				if (connection != null) connection.close();
				System.out.println("🔒 数据库连接已关闭");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}