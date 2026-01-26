package com.example.SimpleHttp.service;

import com.example.SimpleHttp.entity.User;
import com.example.SimpleHttp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 1. 标记这是一个Spring业务逻辑组件（Bean）
public class UserService {

    @Autowired // 2. 自动注入UserRepository实例
    private UserRepository userRepository;

    /**
    /**
     * 创建一个新用户
     * @Transactional 注解确保方法在一个数据库事务中执行
     */
    @Transactional
    public User createUser(String username, String email) {
        // 可以在这里添加业务逻辑，例如检查用户名是否已存在
        // if (userRepository.findByUsername(username) != null) {
        //     throw new RuntimeException("用户名已存在");
        // }

        User user = new User(username, email);
        // 调用Repository的save方法，将用户对象持久化到数据库
        return userRepository.save(user);
    }

    /**
     * 更新指定ID的用户信息
     * @param id 用户ID
     * @param username 新的用户名（可为null，表示不更新）
     * @param email 新的邮箱（可为null，表示不更新）
     * @return 更新后的用户对象
     * @throws RuntimeException 如果用户不存在
     */
    @Transactional
    public User updateUser(Long id, String username, String email) {
        // 1. 先查找用户是否存在
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("用户不存在，ID: " + id));

        // 2. 更新用户信息（如果参数不为null才更新）
        if (username != null && !username.trim().isEmpty()) {
            user.setUsername(username);
        }

        if (email != null && !email.trim().isEmpty()) {
            user.setEmail(email);
        }

        // 3. 保存更新（JPA会自动检测到对象变化并生成UPDATE语句）
        return userRepository.save(user);
    }

    /**
     * 获取所有用户列表
     */
    public List<User> getAllUsers() {
        // 调用Repository的findAll方法
        return userRepository.findAll();
    }

    /**
     * 根据ID查询单个用户
     */
    public User getUserById(Long id) {
        // findById返回一个Optional对象，orElse(null)表示如果没找到则返回null
        return userRepository.findById(id).orElse(null);
    }

    /**
     * 根据ID删除用户
     */
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * 获取用户总数
     */
    public long countUsers() {
        return userRepository.count();
    }
}