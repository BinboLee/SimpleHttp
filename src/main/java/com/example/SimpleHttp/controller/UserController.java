package com.example.SimpleHttp.controller;

import com.example.SimpleHttp.entity.User;
import com.example.SimpleHttp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // 1. 标记这是RESTful风格的控制器，返回JSON数据
@RequestMapping("/api/users") // 2. 定义所有接口的基础路径
public class UserController {

    @Autowired // 3. 注入业务逻辑层
    private UserService userService;

    /**
     * 创建用户
     * POST /api/users
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String email = requestBody.get("email");

        // 简单的参数校验
        if (username == null || username.trim().isEmpty() || email == null || email.trim().isEmpty()) {
            // 如果参数无效，返回400错误
            return ResponseEntity.badRequest().build();
        }

        User user = userService.createUser(username, email);
        // 创建成功，返回201状态码和创建的用户对象
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,  // 从路径中获取用户ID
            @RequestBody Map<String, String> requestBody) {  // 获取请求体JSON

        try {
            // 从请求体中获取参数（允许部分更新）
            String username = requestBody.get("username");
            String email = requestBody.get("email");

            // 调用Service的更新方法
            User updatedUser = userService.updateUser(id, username, email);

            // 返回更新后的用户信息
            return ResponseEntity.ok(updatedUser);

        } catch (RuntimeException e) {
            // 用户不存在或其他错误
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * 获取所有用户
     * GET /api/users
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * 根据ID获取用户详情
     * GET /api/users/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            // 用户不存在，返回404
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * 获取用户总数（一个简单的统计接口）
     * GET /api/users/count
     */
    @GetMapping("/count")
    public Map<String, Long> getCount() {
        Map<String, Long> response = new HashMap<>();
        response.put("count", userService.countUsers());
        return response;
    }

    /**
     * 根据ID删除用户
     * DELETE /api/users/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.getUserById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        // 删除成功，返回204 No Content
        return ResponseEntity.noContent().build();
    }
}