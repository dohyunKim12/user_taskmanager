package com.bos.usertaskmanager.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserTaskInput {
    private TaskInput task;
    private String directory;
    private String env;
    private String description;
}
