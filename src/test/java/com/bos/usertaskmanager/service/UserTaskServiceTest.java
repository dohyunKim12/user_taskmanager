package com.bos.usertaskmanager.service;

import com.bos.usertaskmanager.dto.CreateUserTaskInput;
import com.bos.usertaskmanager.dto.TaskInput;
import com.bos.usertaskmanager.exception.InvalidTaskInputException;
import com.bos.usertaskmanager.model.Task;
import com.bos.usertaskmanager.model.User;
import com.bos.usertaskmanager.model.UserTask;
import com.bos.usertaskmanager.repository.TaskMapper;
import com.bos.usertaskmanager.repository.UserMapper;
import com.bos.usertaskmanager.repository.UserTaskMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserTaskServiceTest {
    Logger logger = LoggerFactory.getLogger(UserTaskServiceTest.class);

    @Mock
    private TaskMapper taskMapper;

    @Mock
    private UserTaskMapper userTaskMapper;

    @InjectMocks
    private UserTaskService userTaskService;

    @Test
    void testCreateUserTask_Success() {
        //Arrange
        CreateUserTaskInput input = new CreateUserTaskInput();
        TaskInput taskInput = new TaskInput();
        input.setTask(taskInput);
        input.setEnv("prod");
        input.setDirectory("/test/dir");
        input.setDescription("test description");

        when(taskMapper.getNextTaskId()).thenReturn("TSK-000001");
        when(userTaskMapper.getNextUserTaskId()).thenReturn("UTK-000001");
        doNothing().when(taskMapper).insertTask(any(Task.class));
        doNothing().when(userTaskMapper).insertUserTask(any(UserTask.class));

        // Act
        String userTaskId = userTaskService.createUserTask(input);

        // Assert
        assertNotNull(userTaskId);
        assertEquals("TSK-000001", userTaskId);

        verify(taskMapper).insertTask(any(Task.class));
        verify(userTaskMapper).insertUserTask(any(UserTask.class));
    }

    @Test
    void testCreateUserTask_InvalidInput() {
        InvalidTaskInputException ex = assertThrows(
                InvalidTaskInputException.class,
                () -> userTaskService.createUserTask(null)
        );

        assertEquals("Task input or details cannot be null", ex.getMessage());

        verify(taskMapper, never()).insertTask(any(Task.class));
        verify(userTaskMapper, never()).insertUserTask(any(UserTask.class));

        CreateUserTaskInput input = new CreateUserTaskInput();
        input.setTask(new TaskInput());

        InvalidTaskInputException ex2 = assertThrows(
                InvalidTaskInputException.class,
                () -> userTaskService.createUserTask(input)
        );

        assertEquals("directory", ex2.getDetails().get("missingField"));

        verify(taskMapper, never()).insertTask(any(Task.class));
        verify(userTaskMapper, never()).insertUserTask(any(UserTask.class));
    }
}
