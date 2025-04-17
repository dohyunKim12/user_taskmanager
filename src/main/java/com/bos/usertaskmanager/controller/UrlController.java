package com.bos.usertaskmanager.controller;

import com.bos.usertaskmanager.dto.MakeShortUrlInDto;
import com.bos.usertaskmanager.dto.MakeShortUrlOutDto;
import com.bos.usertaskmanager.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }


    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToOriginalUrl(
            @PathVariable String shortUrl,
            HttpServletRequest request
    ) {
        return urlService.getOriginalUrlAndTrack(
                        shortUrl,
                        request.getRemoteAddr(),
                        request.getHeader("User-Agent"))
                .map(originalUrl -> ResponseEntity.status(HttpStatus.FOUND)
                        .location(URI.create(originalUrl))
                        .<Void>build())
                .orElse(ResponseEntity.notFound().build());
    }




    @PostMapping("/short-url")
    public MakeShortUrlOutDto makeShortUrl(@Valid @RequestBody MakeShortUrlInDto dto) {
        return urlService.makeShortUrl(dto);
    }
}
