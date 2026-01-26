package com.example.SimpleHttp.service;

import com.example.SimpleHttp.entity.WTS;
import com.example.SimpleHttp.repository.WTSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WTSService {

    @Autowired
    private WTSRepository wtsRepository; // 小驼峰命名规范

    /**
     * 创建WTS记录（接收3个标签值，内部拼接成sentence）
     * @param word1 标签1
     * @param word2 标签2
     * @param word3 标签3
     * @return 保存到数据库的WTS对象（包含自增ID）
     */
    @Transactional
    public WTS createWTS(String word1, String word2, String word3) {
        // 1. Service层完成拼接（可自定义分隔符，这里用空格）
        String sentence = word1 + " " + word2 + " " + word3;

        // 2. 创建WTS对象并保存
        WTS wts = new WTS(sentence);
        return wtsRepository.save(wts);
    }

}