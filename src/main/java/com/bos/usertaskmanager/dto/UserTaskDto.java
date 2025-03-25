package com.bos.usertaskmanager.dto;

import lombok.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTaskDto {
    // task table field
    private String taskId;
    private String taskType;
    private String userId;
    private Float requestedCpu;
    private Float requestedMem;
    private String licenseType;
    private Integer licenseCount;
    private Float timelimit;
    private String command;
    private String partition;
    private String status;
    private Float priority;
    private Timestamp submittedAt;
    private Timestamp startedAt;
    private Timestamp endedAt;

    // user_task table field
    private String userTaskId;
    private String directory;
    private String env;
    private String description;
    private Integer exitCode;
}
