package com.bos.usertaskmanager.service;

import com.bos.usertaskmanager.dto.MakeShortUrlInDto;
import com.bos.usertaskmanager.dto.MakeShortUrlOutDto;
import com.bos.usertaskmanager.model.User;
import com.bos.usertaskmanager.repository.TeamLicenseMapper;
import com.bos.usertaskmanager.repository.UrlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UrlService {
    Logger logger = LoggerFactory.getLogger(UrlService.class);

    @Autowired
    private CacheManager cacheManager;

    private final Map<String, String> storage = new ConcurrentHashMap<>();
    private final UrlService self;

    private final UrlStatsService urlStatsService;

    public UrlService(@Lazy UrlService self, UrlStatsService urlStatsService) { // @Lazy 어노테이션을 사용하여 순환 참조를 방지
        this.self = self;
        this.urlStatsService = urlStatsService;
    }

    @CachePut(value = "urlCache", key = "#shortCode")
    public String cacheOriginalUrl(String shortCode, String originalUrl) {
        return originalUrl;  // 캐시에는 originalUrl (String)만 저장
    }

    @Cacheable(value = "urlCache", key = "#shortUrl")
    public String getOriginalUrl(String shortUrl) {
        return storage.get(shortUrl);
    }

    public MakeShortUrlOutDto makeShortUrl(MakeShortUrlInDto dto) {
        // Make short url use UUID (8 characters)
        String shortUrl = UUID.randomUUID().toString().substring(0, 8);
        String originalUrl = dto.getOriginalUrl();

        // Store the short url in db & memory (mapping with original url)
        storage.put(shortUrl, originalUrl);
        self.cacheOriginalUrl(shortUrl, originalUrl);

        MakeShortUrlOutDto response = new MakeShortUrlOutDto();
        response.setOriginalUrl(originalUrl);
        response.setShortCode(shortUrl);
        response.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        return response;
    }

    @Transactional
    public Optional<String> getOriginalUrlAndTrack(String shortUrl, String ip, String userAgent) {
        String originalUrl = self.getOriginalUrl(shortUrl);
        if(originalUrl == null) {
            return Optional.empty();
        } else {
            urlStatsService.recordAccess(shortUrl, ip, userAgent, LocalDateTime.now());
            return Optional.of(originalUrl);
        }
    }
}
