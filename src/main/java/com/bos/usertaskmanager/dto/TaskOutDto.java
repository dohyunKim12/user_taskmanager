package com.bos.usertaskmanager.dto;

import com.bos.usertaskmanager.constant.PriorityTier;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskOutDto {
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
    private PriorityTier priorityTier;
    private String submittedAt;
    private String startedAt;
    private String endedAt;
}