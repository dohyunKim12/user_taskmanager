package com.bos.usertaskmanager.graphql;

import com.bos.usertaskmanager.model.UserTask;
import com.bos.usertaskmanager.model.UserTaskDetail;
import com.bos.usertaskmanager.service.UserTaskService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTaskResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

//    @Autowired
//    private UserTaskService userTaskService;
    private final UserTaskService userTaskService;
    public UserTaskResolver(UserTaskService userTaskService) {
        this.userTaskService = userTaskService;
    }

    public UserTask getUserTask(String userTaskId) {
        return userTaskService.getUserTaskById(userTaskId);
    }

    public List<UserTask> getAllUserTasks() {
        return userTaskService.getAllUserTasks();
    }

    public UserTask createUserTask(String userId, UserTaskDetail detail) {
        UserTask userTask = new UserTask();
        userTask.setUserId(userId);
        userTask.setUserTaskDetail(detail);
        return userTaskService.createUserTask(userTask);
    }

    public Boolean deleteUserTask(String userTaskId) {
        return userTaskService.deleteUserTask(userTaskId);
    }
}
