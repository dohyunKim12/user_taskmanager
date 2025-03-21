package com.bos.usertaskmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String userId;
    private String teamId;
    private String teamName;
    private String username;
    private String email;
    private String password;
    private String role;
}