package com.bos.usertaskmanager.graphql;

import com.bos.usertaskmanager.model.UserTask;
import com.bos.usertaskmanager.model.UserTaskDetail;
import com.bos.usertaskmanager.service.UserTaskService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserTaskResolver {
    private final UserTaskService userTaskService;
    public UserTaskResolver(UserTaskService userTaskService) {
        this.userTaskService = userTaskService;
    }

    @QueryMapping
    public UserTask getUserTask(@Argument String userTaskId) {
        return userTaskService.getUserTaskById(userTaskId);
    }

    @QueryMapping
    public List<UserTask> getAllUserTasks() {
        return userTaskService.getAllUserTasks();
    }


    @MutationMapping
    public UserTask createUserTask(@Argument String userId, @Argument UserTaskDetail detail) {
        UserTask userTask = new UserTask();
        userTask.setUserId(userId);
        userTask.setUserTaskDetail(detail);
        return userTaskService.createUserTask(userTask);
    }

    @MutationMapping
    public Boolean deleteUserTask(@Argument String userTaskId) {
        return userTaskService.deleteUserTask(userTaskId);
    }
}
