package com.bos.usertaskmanager.dto;

import lombok.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemTaskDto {
    // task
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

    // system_task
    private String systemTaskId;
    private String systemType;
    private String config;
}
