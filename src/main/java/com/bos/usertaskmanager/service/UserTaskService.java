package com.bos.usertaskmanager.service;

import com.bos.usertaskmanager.model.UserTask;
import com.bos.usertaskmanager.repository.UserTaskDetailMapper;
import com.bos.usertaskmanager.repository.UserTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTaskService {
    @Autowired
    private UserTaskMapper userTaskMapper;
    @Autowired
    private UserTaskDetailMapper userTaskDetailMapper;

    public UserTask getUserTaskById(String userTaskId) {
        return userTaskMapper.getUserTaskById(userTaskId);
    }

    public List<UserTask> getAllUserTasks() {
        return userTaskMapper.findAll();
    }

    public UserTask createUserTask(UserTask userTask) {
        String userTaskId = userTaskMapper.insertUserTask(userTask);
        userTask.getUserTaskDetail().setUserTaskId(userTaskId);
        userTaskDetailMapper.insertUserTaskDetails(userTask.getUserTaskDetail());
        return userTask;
    }

    public UserTask updateUserTask(UserTask userTask) {
        userTaskMapper.updateUserTask(userTask);
        return userTask;
    }

    public boolean deleteUserTask(String userTaskId) {
        return userTaskMapper.deleteUserTaskById(userTaskId) > 0;
    }
}
