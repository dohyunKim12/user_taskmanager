package com.bos.usertaskmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTaskInputException extends RuntimeException {
    private final String errorCode;
    private final Map<String, Object> details;

    public InvalidTaskInputException(String message, Map<String, Object> details) {
        super(message);
        this.errorCode = ErrorCode.INVALID_TASK_INPUT.getCode();
        this.details = details;
    }

    public InvalidTaskInputException(String message) {
        super(message);
        this.errorCode = ErrorCode.INVALID_TASK_INPUT.getCode();
        this.details = new HashMap<>();
    }
}
