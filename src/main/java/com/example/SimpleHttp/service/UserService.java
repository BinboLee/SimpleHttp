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