package com.bos.usertaskmanager.model;

import com.bos.usertaskmanager.dto.TaskInput;
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

    public static Task fromInput(TaskInput input) {
        Task task = new Task();
        task.setUserId(input.getUserId());
        task.setRequestedCpu(input.getRequestedCpu());
        task.setRequestedMem(input.getRequestedMem());
        task.setLicenseType(input.getLicenseType());
        task.setLicenseCount(input.getLicenseCount());
        task.setTimelimit(input.getTimelimit());
        task.setCommand(input.getCommand());
        task.setPartition(input.getPartition());
        task.setPriority(input.getPriority() != null ? input.getPriority() : 0.0f);

        return task;
    }
}