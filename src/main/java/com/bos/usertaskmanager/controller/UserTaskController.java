package com.bos.usertaskmanager.controller;

import com.bos.usertaskmanager.dto.CreateUserTaskInput;
import com.bos.usertaskmanager.dto.ResultDto;
import com.bos.usertaskmanager.dto.TaskFilterInput;
import com.bos.usertaskmanager.dto.UserTaskOutDto;
import com.bos.usertaskmanager.repository.TaskMapper;
import com.bos.usertaskmanager.service.UserTaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserTaskController {
    private final UserTaskService userTaskService;

    public UserTaskController(UserTaskService userTaskService, TaskMapper taskMapper) {
        this.userTaskService = userTaskService;
    }

    @GetMapping("/userTask")
    public UserTaskOutDto getUserTaskRest(@RequestParam("userTaskId") String userTaskId) {
        return userTaskService.getUserTaskById(userTaskId);
    }

    @GetMapping("/userTasks")
    public List<UserTaskOutDto> getAllUserTasksRest() {
        return userTaskService.getAllUserTasks();
    }

    @PostMapping("/userTasks")
    public List<UserTaskOutDto> getFilteredUserTasksRest(@RequestBody TaskFilterInput filters) {
        if(filters != null) {
            filters.sanitize();
        }
        return userTaskService.getFilteredUserTasks(filters);
    }

    @PostMapping("/createUserTask")
    public String createUserTaskRest(@RequestBody CreateUserTaskInput input) {
        return userTaskService.createUserTask(input);
    }

    @DeleteMapping("/deleteUserTask/{userTaskId}")
    public ResultDto deleteUserTaskRest(@PathVariable String userTaskId) {
        if(userTaskService.deleteUserTask(userTaskId)){
            return new ResultDto(true, "User Task deleted successfully");
        } else {
            return new ResultDto(false, "User Task not found");
        }
    }
}
