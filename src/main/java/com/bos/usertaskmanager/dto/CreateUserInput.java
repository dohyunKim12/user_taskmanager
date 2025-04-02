package com.bos.usertaskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserInput {
    private String teamId;
    private String username;
    private String email;
    private String password;
    private String role;
}
