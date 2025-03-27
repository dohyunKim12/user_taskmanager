package com.bos.usertaskmanager.service;

import com.bos.usertaskmanager.dto.CreateUserTaskInput;
import com.bos.usertaskmanager.dto.TaskFilterInput;
import com.bos.usertaskmanager.dto.UserTaskOutDto;
import com.bos.usertaskmanager.model.Task;
import com.bos.usertaskmanager.model.UserTask;
import com.bos.usertaskmanager.repository.TaskMapper;
import com.bos.usertaskmanager.repository.UserTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserTaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UserTaskMapper userTaskMapper;

    public List<UserTaskOutDto> getAllUserTasks() {
        return userTaskMapper.getAllUserTasks();
    }

    public UserTaskOutDto getUserTaskById(String userTaskId) {
        return userTaskMapper.getUserTaskById(userTaskId);
    }

    public List<UserTaskOutDto> getFilteredUserTasks(TaskFilterInput filters) {
        return userTaskMapper.getFilteredUserTasks(filters);
    }

    public UserTask createUserTask(UserTask userTask, Task task) {
        String taskId = taskMapper.getNextTaskId();

        task.setTaskId(taskId);
        task.setTaskType("user");
        taskMapper.insertTask(task);

        // UserTask insert
        userTask.setTaskId(taskId);
        userTaskMapper.insertUserTask(userTask);

        return userTask;
    }

    public UserTaskOutDto updateUserTask(UserTask userTask, Task task) {
        taskMapper.updateTaskFields(task);
        userTaskMapper.updateUserTask(userTask);
        return userTaskMapper.getUserTaskById(userTask.getUserTaskId());
    }

    public boolean deleteUserTask(String userTaskId) {
        String taskId = userTaskMapper.getTaskIdByUserTaskId(userTaskId);
        int deletedTask = taskMapper.deleteTaskById(taskId);
        return deletedTask > 0;
    }

    public String createUserTask(CreateUserTaskInput input) {
        String taskId = taskMapper.getNextTaskId();
        String userTaskId = userTaskMapper.getNextUserTaskId();

        Task task = Task.fromInput(input.getTask());
        task.setTaskType("user");
        task.setTaskId(taskId);
        task.setStatus("pending");
        task.setSubmittedAt(Timestamp.valueOf(LocalDateTime.now()));
        taskMapper.insertTask(task);

        // UserTask insert
        UserTask userTask = new UserTask();
        userTask.setUserTaskId(userTaskId);
        userTask.setTaskId(taskId);
        userTask.setDirectory(input.getDirectory());
        userTask.setEnv(input.getEnv());
        userTask.setDescription(input.getDescription());
        userTaskMapper.insertUserTask(userTask);

        return userTaskId;
    }
}
