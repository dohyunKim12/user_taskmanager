package com.bos.usertaskmanager.conroller;

import com.bos.usertaskmanager.model.UserTask;
import com.bos.usertaskmanager.service.UserTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final UserTaskService taskService;

    @GetMapping
    public List<UserTask> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public Integer addTask(@RequestBody UserTask task) {
        return taskService.addTask(task);
    }

    @PutMapping("/{id}")
    public void updateTask(@PathVariable Long id, @RequestBody UserTask task) {
        task.setId(id);
        taskService.updateTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
