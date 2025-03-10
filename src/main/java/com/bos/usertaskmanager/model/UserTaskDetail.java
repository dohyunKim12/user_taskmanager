package com.bos.usertaskmanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserTaskDetail {
    private String userTaskId;
    private String command;
    private String licenseType;
    private Integer licenseCount;
    private String directory;
    private String uuid;
    private Float timelimit;
    private Float requestedCpu;
    private Float requestedMem;
}
