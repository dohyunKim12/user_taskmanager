package com.bos.usertaskmanager.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MakeShortUrlOutDto {
    private String originalUrl;
    private String shortCode;
    private Timestamp createdAt;
}
