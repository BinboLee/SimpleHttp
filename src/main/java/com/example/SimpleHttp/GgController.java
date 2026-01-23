package com.example.SimpleHttp;

import org.springframework.web.bind.annotation.*;

@RestController
public class GgController {


    // 或者更具体的写法，处理所有HTTP方法和所有路径
    @RequestMapping(path = "/api", method = {RequestMethod.GET, RequestMethod.POST,
            RequestMethod.PUT, RequestMethod.DELETE,
            RequestMethod.PATCH})
    public String handleAll() {
        return "GG";
    }
}