package com.example.SimpleHttp.controller;

import com.example.SimpleHttp.entity.WTS;
import com.example.SimpleHttp.service.WTSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/WTS")
public class WTSController {

    @Autowired
    private WTSService wtsService;

    /**
     * 生成句子：接收word1/word2/word3三个标签，传给Service拼接并保存
     * POST /api/WTS
     * 请求体示例：{"word1":"春天","word2":"花开","word3":"微风"}
     */
    @PostMapping
    public ResponseEntity<WTS> createWTS(@RequestBody Map<String, String> requestBody) {
        // 1. 从请求体提取三个标签值
        String word1 = requestBody.get("word1");
        String word2 = requestBody.get("word2");
        String word3 = requestBody.get("word3");

        // 2. 严格参数校验：三个标签都不能为空/空字符串（trim去首尾空格）
        if (word1 == null || word1.trim().isEmpty()
                || word2 == null || word2.trim().isEmpty()
                || word3 == null || word3.trim().isEmpty()) {
            // 参数无效返回400 Bad Request
            return ResponseEntity.badRequest().build();
        }

        // 3. 直接传递3个原始值给Service（不再拼接）
        WTS wts = wtsService.createWTS(word1.trim(), word2.trim(), word3.trim());

        // 4. 返回201状态码 + 保存后的WTS对象
        return ResponseEntity.status(HttpStatus.CREATED).body(wts);
    }

}