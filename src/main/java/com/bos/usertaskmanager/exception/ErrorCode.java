package com.bos.usertaskmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_TASK_INPUT("E4001", "Invalid Task Input"),
    TASK_VALIDATION_FAILED("E4002", "Task validation failed"),
    FIELD_VALIDATION_FAILED("E4003", "Field validation failed"),
    INPUT_SCOPE_OVERFLOW("E4004", "Input scope overflow");

    private final String code;
    private final String description;
}