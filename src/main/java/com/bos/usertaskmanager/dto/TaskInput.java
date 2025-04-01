package com.bos.usertaskmanager.dto;

import com.bos.usertaskmanager.constant.PriorityTier;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskInput {
    private String userId;
    private Float requestedCpu;
    private Float requestedMem;
    private String licenseType;
    private Integer licenseCount;
    private Float timelimit;
    private String command;
    private String partition;
    private Float priority;
    private PriorityTier priorityTier = PriorityTier.NORMAL;
    private String status;
    private String startedAt;
    private String endedAt;
}
