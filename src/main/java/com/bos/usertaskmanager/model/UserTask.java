package com.bos.usertaskmanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserTask {
    private String userTaskId;  // USR-000001
    private Integer jobId;
    private String shortCmd;
    private Integer userId;
    private LocalDateTime submittedAt;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String status; // pending, running, completed, cancelled
}
