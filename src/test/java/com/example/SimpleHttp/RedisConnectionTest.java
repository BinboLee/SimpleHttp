package com.example.SimpleHttp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisConnectionTest {

    // 自动注入StringRedisTemplate（无需手动创建）
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedisConnection() {
        // 1. 写入Redis键值对
        stringRedisTemplate.opsForValue().set("springboot_redis_test", "hello_redis_6375559");

        // 2. 从Redis读取值
        String value = stringRedisTemplate.opsForValue().get("springboot_redis_test");

        // 3. 打印结果验证
        System.out.println("从Redis读取到的值：" + value);
    }
}