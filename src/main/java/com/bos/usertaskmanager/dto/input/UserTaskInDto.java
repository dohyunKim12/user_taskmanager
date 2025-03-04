package com.bos.usertaskmanager.dto.input;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserTaskInDto {
    private String taskName;
    private String description;
    private String status;
}
