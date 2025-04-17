package com.bos.usertaskmanager.controller;

import com.bos.usertaskmanager.dto.TypicodePostDto;
import com.bos.usertaskmanager.util.HttpUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ApiController {

    private final HttpUtil httpUtil;

    public ApiController(HttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    @GetMapping("/call/external/get")
    public String callExternalApi(@RequestParam("query") String query) {
        String apiUrl = "https://jsonplaceholder.typicode.com/posts/" + query;
        String response = httpUtil.get(apiUrl);
        return response;
    }

    @PostMapping("/call/external/post")
    public String callExternalPostApi(@RequestBody TypicodePostDto dto) {
        String apiUrl = "https://jsonplaceholder.typicode.com/posts";

        String response = httpUtil.post(apiUrl, dto);

        return response;
    }
}
