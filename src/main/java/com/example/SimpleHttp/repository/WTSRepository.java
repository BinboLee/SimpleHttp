package com.example.SimpleHttp.repository;

import com.example.SimpleHttp.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 此注解可选，Spring会自动识别[citation:2]
public interface WTSRepository extends JpaRepository<WTS, Long> {
    // 目前不需要写任何方法！
    // JpaRepository 已经提供了基础的CRUD方法[citation:1][citation:3][citation:10]
}