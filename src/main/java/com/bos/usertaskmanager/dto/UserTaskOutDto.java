package com.bos.usertaskmanager.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTaskOutDto {
    // user_task table field
    private String userTaskId;
    private String username;
    private String directory;
    private String env;
    private String description;
    private Integer exitCode;
    // task table field
    private TaskOutDto task;
}
