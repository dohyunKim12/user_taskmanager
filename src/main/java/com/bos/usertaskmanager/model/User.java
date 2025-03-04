package com.bos.usertaskmanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private String userId;
    private String teamId;
    private String username;
    private String email;
}