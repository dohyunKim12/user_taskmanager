package com.bos.usertaskmanager.service;

import com.bos.usertaskmanager.dto.UrlAccessLog;
import com.bos.usertaskmanager.dto.UrlStatsDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UrlStatsService {

    // 각 shortUrl 별로 접근 기록을 저장하는 맵
    private final Map<String, List<UrlAccessLog>> accessLogs = new ConcurrentHashMap<>();

    // 접근 기록 저장 메서드
    public void recordAccess(String shortUrl, String ip, String userAgent, LocalDateTime accessedAt) {
        UrlAccessLog log = new UrlAccessLog(ip, userAgent, accessedAt);
        accessLogs.computeIfAbsent(shortUrl, k -> new LinkedList<>());

        List<UrlAccessLog> logs = accessLogs.get(shortUrl);

        synchronized (logs) {
            logs.add(0, log);  // 최신 기록을 맨 앞에 추가
            if (logs.size() > 10) {
                logs.remove(logs.size() - 1);  // 최근 10개만 유지
            }
        }
    }

    // 특정 shortUrl에 대한 통계 조회
    public UrlStatsDto getStats(String shortUrl, String originalUrl, LocalDateTime createdAt) {
        List<UrlAccessLog> logs = accessLogs.getOrDefault(shortUrl, Collections.emptyList());

        return UrlStatsDto.builder()
                .shortCode(shortUrl)
                .originalUrl(originalUrl)
                .totalClicks(logs.size())
                .createdAt(createdAt)
                .lastAccessedAt(logs.isEmpty() ? null : logs.get(0).getAccessedAt())
                .recentAccesses(new ArrayList<>(logs))
                .build();
    }
}
