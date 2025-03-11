package com.bos.usertaskmanager.service;

import com.bos.usertaskmanager.model.User;
import com.bos.usertaskmanager.model.UserTask;
import com.bos.usertaskmanager.repository.UserMapper;
import com.bos.usertaskmanager.repository.UserTaskDetailMapper;
import com.bos.usertaskmanager.repository.UserTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserTaskService {
    @Autowired
    private UserTaskMapper userTaskMapper;
    @Autowired
    private UserTaskDetailMapper userTaskDetailMapper;

    public UserTask getUserTaskById(String userTaskId) {
        return userTaskMapper.getUserTaskById(userTaskId);
    }

    public List<UserTask> getAllUserTasks() {
        return userTaskMapper.getAllUserTasks();
    }

    public UserTask createUserTask(UserTask userTask) {
        String userTaskId = userTaskMapper.getNextTaskId();
        userTask.setUserTaskId(userTaskId);
        userTaskMapper.insertUserTask(userTask);
        userTask.getUserTaskDetail().setUserTaskId(userTaskId);
        userTaskDetailMapper.insertUserTaskDetail(userTask.getUserTaskDetail());
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
