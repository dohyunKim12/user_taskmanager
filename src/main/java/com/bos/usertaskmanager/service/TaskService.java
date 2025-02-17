package com.bos.usertaskmanager.service;

import com.bos.usertaskmanager.model.Task;
import com.bos.usertaskmanager.repository.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskMapper taskMapper;

    public List<Task> getAllTasks() {
        return taskMapper.findAll();
    }

    public Integer addTask(Task task) {
        return taskMapper.insertTask(task);
    }

    public void updateTask(Task task) {
        taskMapper.updateTask(task);
    }

    public void deleteTask(Long id) {
        taskMapper.deleteById(id);
    }
}
