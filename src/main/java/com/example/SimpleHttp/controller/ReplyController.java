package com.example.SimpleHttp.controller;

import com.example.SimpleHttp.entity.Reply;
import com.example.SimpleHttp.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/replies")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    /**
     * 创建新记录
     * POST /api/replies
     */
    @PostMapping
    public ResponseEntity<Reply> createReply(@RequestBody Map<String, String> requestBody) {
        String req = requestBody.get("req");
        String rep = requestBody.get("rep");

        if (req == null || rep == null) {
            return ResponseEntity.badRequest().build();
        }

        Reply reply = replyService.createReply(req, rep);
        return ResponseEntity.status(HttpStatus.CREATED).body(reply);
    }

    /**
     * 批量创建
     * POST /api/replies/batch
     */
    @PostMapping("/batch")
    public ResponseEntity<List<Reply>> createReplies(@RequestBody List<Reply> replies) {
        List<Reply> createdReplies = replyService.createReplies(replies);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReplies);
    }

    /**
     * 获取所有记录
     * GET /api/replies
     */
    @GetMapping
    public List<Reply> getAllReplies() {
        return replyService.getAllReplies();
    }

    /**
     * 根据ID获取记录
     * GET /api/replies/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Reply> getReplyById(@PathVariable Long id) {
        try {
            Reply reply = replyService.getReplyById(id);
            return ResponseEntity.ok(reply);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    /**
     * 根据Req查询
     * GET /api/replies/req/{req}
     */
    @GetMapping("/req/{req}")
    public List<Reply> getRepliesByReq(@PathVariable String req) {
        return replyService.getRepliesByReq(req);
    }

    /**
     * 根据Rep查询
     * GET /api/replies/rep/{rep}
     */
    @GetMapping("/rep/{rep}")
    public List<Reply> getRepliesByRep(@PathVariable String rep) {
        return replyService.getRepliesByRep(rep);
    }

    /**
     * 更新记录
     * PUT /api/replies/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Reply> updateReply(
            @PathVariable Long id,
            @RequestBody Map<String, String> requestBody) {

        try {
            String req = requestBody.get("req");
            String rep = requestBody.get("rep");

            Reply updatedReply = replyService.updateReply(id, req, rep);
            return ResponseEntity.ok(updatedReply);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    /**
     * 删除记录
     * DELETE /api/replies/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReply(@PathVariable Long id) {
        try {
            replyService.deleteReply(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 统计记录数
     * GET /api/replies/count
     */
    @GetMapping("/count")
    public Map<String, Long> countReplies() {
        long count = replyService.countReplies();
        return Map.of("count", count);
    }
}