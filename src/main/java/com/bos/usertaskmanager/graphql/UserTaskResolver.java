package com.bos.usertaskmanager.graphql;

import com.bos.usertaskmanager.dto.CreateUserTaskInput;
import com.bos.usertaskmanager.dto.ResultDto;
import com.bos.usertaskmanager.dto.TaskFilterInput;
import com.bos.usertaskmanager.dto.UserTaskOutDto;
import com.bos.usertaskmanager.model.Task;
import com.bos.usertaskmanager.model.UserTask;
import com.bos.usertaskmanager.repository.TaskMapper;
import com.bos.usertaskmanager.service.UserTaskService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserTaskResolver {
    private final UserTaskService userTaskService;
    private final TaskMapper taskMapper;

    public UserTaskResolver(UserTaskService userTaskService, TaskMapper taskMapper) {
        this.userTaskService = userTaskService;
        this.taskMapper = taskMapper;
    }

    @QueryMapping
    public UserTaskOutDto getUserTask(@Argument String userTaskId) {
        return userTaskService.getUserTaskById(userTaskId);
    }

    @QueryMapping
    public List<UserTaskOutDto> getAllUserTasks() {
        return userTaskService.getAllUserTasks();
    }

    @QueryMapping
    public List<UserTaskOutDto> getFilteredUserTasks(@Argument TaskFilterInput filters) {
        if(filters != null) {
            filters.sanitize();
        }
        return userTaskService.getFilteredUserTasks(filters);
    }


    @MutationMapping
    public String createUserTask(@Argument CreateUserTaskInput input) {
        return userTaskService.createUserTask(input);
    }


    @MutationMapping
    public ResultDto deleteUserTask(@Argument String userTaskId) {
        if(userTaskService.deleteUserTask(userTaskId)){
            return new ResultDto(true, "User Task deleted successfully");
        } else {
            return new ResultDto(false, "User Task not found");
        }
    }

//    @SchemaMapping(typeName = "UserTask", field = "task")
//    public Task resolveTask(UserTaskOutDto userTask) {
//        return taskMapper.getTaskById(userTask.getTask().getTaskId());
//    }
}
