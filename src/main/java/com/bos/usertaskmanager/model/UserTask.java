package com.bos.usertaskmanager.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTask {
    private String userTaskId;
    private String taskId;
    private String directory;
    private String env;
    private String description;
    private Integer exitCode;
}
