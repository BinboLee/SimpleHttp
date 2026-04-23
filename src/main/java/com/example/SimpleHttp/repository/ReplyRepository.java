package com.example.SimpleHttp.repository;

import com.example.SimpleHttp.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // 根据Req查询
    List<Reply> findByReq(String req);//这是JPA标准写法，List<Reply>表示可能返回多个Reply结果，因为查询条件可能有多个，findByReq(String req)是JPA标准写法，会自动转换成sql，SELECT * FROM reply WHERE req = ?

    // 根据Rep查询
    List<Reply> findByRep(String rep);

    // 根据Req和Rep同时查询
    List<Reply> findByReqAndRep(String req, String rep);

    // 查询Req包含指定字符串的记录
    List<Reply> findByReqContaining(String keyword);

    // 自定义查询：根据Req模糊查询
    // @Query("SELECT r FROM Reply r WHERE r.req LIKE %:req%")
    // List<Reply> findCustomByReq(@Param("req") String req);
}