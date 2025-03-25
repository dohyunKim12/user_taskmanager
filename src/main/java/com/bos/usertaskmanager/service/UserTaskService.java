package com.bos.usertaskmanager.service;

import com.bos.usertaskmanager.dto.TaskFilterInput;
import com.bos.usertaskmanager.dto.UserTaskDto;
import com.bos.usertaskmanager.model.Task;
import com.bos.usertaskmanager.model.UserTask;
import com.bos.usertaskmanager.repository.TaskMapper;
import com.bos.usertaskmanager.repository.UserTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserTaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UserTaskMapper userTaskMapper;

    public List<UserTaskDto> getAllUserTasks() {
        return userTaskMapper.getAllUserTasks();
    }

    public UserTaskDto getUserTaskById(String userTaskId) {
        return userTaskMapper.getUserTaskById(userTaskId);
    }

//    public List<UserTask> getFilteredUserTasks(TaskFilterInput filters) {
//        return userTaskMapper.getFilteredUserTasks(filters);
//    }

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

    public UserTaskDto updateUserTask(UserTask userTask, Task task) {
        taskMapper.updateTaskFields(task);
        userTaskMapper.updateUserTask(userTask);
        return userTaskMapper.getUserTaskById(userTask.getUserTaskId());
    }

    public boolean deleteUserTask(String userTaskId, String taskId) {
        int deletedUserTask = userTaskMapper.deleteUserTaskById(userTaskId);
        int deletedTask = taskMapper.deleteTaskById(taskId);
        return deletedUserTask > 0 && deletedTask > 0;
    }
}
