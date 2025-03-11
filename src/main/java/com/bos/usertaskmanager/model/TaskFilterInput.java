package com.bos.usertaskmanager.model;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TaskFilterInput {
    private List<String> statuses;
    private List<String> usernames;
    private List<String> partitions;
    private List<String> licenseTypes;
    private List<String> cpuRange;
    private List<String> memRange;
    private String submittedAfter;
    private String submittedBefore;
}