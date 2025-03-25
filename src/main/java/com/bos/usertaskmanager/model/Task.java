package com.bos.usertaskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
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
}