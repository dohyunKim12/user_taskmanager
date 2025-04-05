package com.bos.usertaskmanager.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class UrlStatsDto {
    private String shortCode;
    private String originalUrl;
    private int totalClicks;
    private LocalDateTime createdAt;
    private LocalDateTime lastAccessedAt;
    private List<UrlAccessLog> recentAccesses;
}
