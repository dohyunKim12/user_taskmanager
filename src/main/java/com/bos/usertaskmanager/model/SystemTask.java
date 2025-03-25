package com.bos.usertaskmanager.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemTask {
    private String systemTaskId;
    private String taskId;
    private String systemType;
    private String config; // Json field but in Mybatis, string
}
