package com.bos.usertaskmanager.dto;

import lombok.Data;

@Data
public class TypicodePostDto {
    private int userId;
    private int id;
    private String title;
    private String body;
}
