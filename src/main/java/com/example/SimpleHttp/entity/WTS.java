package com.example.SimpleHttp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "wordtosentence")
public class WTS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false)
    private String Sentence;

    // 默认构造函数（JPA要求）
    public WTS() {}

    // 带参数的构造函数
    public WTS(String Sentence) {
        this.Sentence = Sentence;
    }

    // Getter 和 Setter 方法
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSentence() { return Sentence; }
    public void setSentence(String Sentence) { this.Sentence = Sentence; }

}