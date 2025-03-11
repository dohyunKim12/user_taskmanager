package com.bos.usertaskmanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class UserTask {
    private String userTaskId;
    private Integer jobId;
    private String shortCmd;
    private String userId;
    private String username;
    private Timestamp submittedAt;
    private Timestamp startedAt;
    private Timestamp endedAt;
    private String status;
    private UserTaskDetail userTaskDetail;
}
