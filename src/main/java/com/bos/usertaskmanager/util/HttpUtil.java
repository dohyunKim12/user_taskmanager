package com.bos.usertaskmanager.util;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpUtil {
    private final RestTemplate restTemplate;

    public HttpUtil() {
        this.restTemplate = new RestTemplate();
    }

    public String get(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

    public String post(String url, Object request) {
        HttpEntity<Object> entity = new HttpEntity<>(request, jsonHeaders());
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return response.getBody();
    }

    public String put(String url, Object request) {
        HttpEntity<Object> entity = new HttpEntity<>(request, jsonHeaders());
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        return response.getBody();
    }

    public String patch(String url, Object request) {
        HttpEntity<Object> entity = new HttpEntity<>(request, jsonHeaders());
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PATCH, entity, String.class);
        return response.getBody();
    }

    public String delete(String url) {
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
        return response.getBody();
    }

    private HttpHeaders jsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
