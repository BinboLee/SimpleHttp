package com.example.SimpleHttp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reply")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Req", length = 10)
    private String req;

    @Column(name = "Rep", length = 10)
    private String rep;

    // 无参构造
    public Reply() {}

    // 有参构造
    public Reply(String req, String rep) {
        this.req = req;
        this.rep = rep;
    }

    // getter/setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReq() { return req; }
    public void setReq(String req) { this.req = req; }

    public String getRep() { return rep; }
    public void setRep(String rep) { this.rep = rep; }

    @Override
    public String toString() {
        return "Reply{id=" + id + ", req='" + req + "', rep='" + rep + "'}";
    }
}