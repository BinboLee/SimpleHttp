package com.example.SimpleHttp.service;

import com.example.SimpleHttp.entity.Reply;
import com.example.SimpleHttp.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    /**
     * 创建一条记录
     */
    @Transactional
    public Reply createReply(String req, String rep) {
        Reply reply = new Reply(req, rep);
        return replyRepository.save(reply);
    }

    /**
     * 批量创建
     */
    @Transactional
    public List<Reply> createReplies(List<Reply> replies) {
        return replyRepository.saveAll(replies);
    }

    /**
     * 获取所有记录
     */
    public List<Reply> getAllReplies() {
        return replyRepository.findAll();
    }

    /**
     * 根据ID获取记录
     */
    public Reply getReplyById(Long id) {
        return replyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reply记录不存在，ID: " + id));
    }

    /**
     * 根据Req查询记录
     */
    public List<Reply> getRepliesByReq(String req) {
        return replyRepository.findByReq(req);
    }

    /**
     * 根据Rep查询记录
     */
    public List<Reply> getRepliesByRep(String rep) {
        return replyRepository.findByRep(rep);
    }

    /**
     * 更新记录
     */
    @Transactional
    public Reply updateReply(Long id, String req, String rep) {
        Reply reply = getReplyById(id);

        if (req != null) {
            reply.setReq(req);
        }

        if (rep != null) {
            reply.setRep(rep);
        }

        return replyRepository.save(reply);
    }

    /**
     * 删除记录
     */
    @Transactional
    public void deleteReply(Long id) {
        if (!replyRepository.existsById(id)) {
            throw new RuntimeException("Reply记录不存在，ID: " + id);
        }
        replyRepository.deleteById(id);
    }

    /**
     * 统计记录数量
     */
    public long countReplies() {
        return replyRepository.count();
    }
}