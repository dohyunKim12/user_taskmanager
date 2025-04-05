package com.bos.usertaskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UrlAccessLog {
    private String ip;
    private String userAgent;
    private LocalDateTime accessedAt;
}
