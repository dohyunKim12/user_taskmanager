package com.bos.usertaskmanager.service;

import com.bos.usertaskmanager.dto.SystemTaskDto;
import com.bos.usertaskmanager.model.Task;
import com.bos.usertaskmanager.model.SystemTask;
import com.bos.usertaskmanager.repository.SystemTaskMapper;
import com.bos.usertaskmanager.repository.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SystemTaskService {

    private final TaskMapper taskMapper;
    private final SystemTaskMapper systemTaskMapper;

    public List<SystemTaskDto> getAllSystemTasks() {
        return systemTaskMapper.findAllSystemTask();
    }

    public SystemTaskDto getSystemTaskById(String systemTaskId) {
        return systemTaskMapper.getSystemTaskById(systemTaskId);
    }

    public SystemTaskDto createSystemTask(SystemTask systemTask, Task task) {
        String taskId = taskMapper.getNextTaskId();
        String systemTaskId = systemTaskMapper.getNextSystemTaskId();

        task.setTaskId(taskId);
        task.setTaskType("system");
        taskMapper.insertTask(task);

        systemTask.setSystemTaskId(systemTaskId);
        systemTask.setTaskId(taskId);
        systemTaskMapper.insertSystemTask(systemTask);

        return systemTaskMapper.getSystemTaskById(systemTaskId);
    }

    public SystemTaskDto updateSystemTask(SystemTask systemTask, Task task) {
        taskMapper.updateTaskFields(task);
        systemTaskMapper.updateSystemTask(systemTask);
        return systemTaskMapper.getSystemTaskById(systemTask.getSystemTaskId());
    }

    public boolean deleteSystemTask(String systemTaskId, String taskId) {
        int deletedSystemTask = systemTaskMapper.deleteSystemTaskById(systemTaskId);
        int deletedTask = taskMapper.deleteTaskById(taskId);
        return deletedSystemTask > 0 && deletedTask > 0;
    }
}
