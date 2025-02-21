package com.bos.usertaskmanager.service;

import com.bos.usertaskmanager.model.Task;
import com.bos.usertaskmanager.model.UserTask;
import com.bos.usertaskmanager.model.UserTaskDetails;
import com.bos.usertaskmanager.repository.UserTaskDetailsMapper;
import com.bos.usertaskmanager.repository.UserTaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTaskService {

    private final UserTaskMapper userTaskMapper;
    private final UserTaskDetailsMapper userTaskDetailsMapper;

    public List<Task> getAllTasks() {
        return taskMapper.findAll();
    }

    public String submitTask(UserTask userTask, UserTaskDetails userTaskDetails) {
        userTaskMapper.insertUserTask(userTask);
        userTaskDetails.setUserTaskId(userTask.getUserTaskId());
        userTaskDetailsMapper.insertUserTaskDetails(userTaskDetails);
        return userTask.getUserTaskId();
    }

    public UserTaskResponseDto getFullUserTaskById(String userTaskId) {
        UserTask userTask = userTaskMapper.getUserTaskById(userTaskId);
        UserTaskDetails userTaskDetails = userTaskDetailsMapper.getTaskDetailsById(userTaskId);

        if (userTask == null || userTaskDetails == null) {
            return null; // 또는 예외 처리
        }

        UserTaskResponseDto dto = new UserTaskResponseDto();
        dto.setUserTaskId(userTask.getUserTaskId());
        dto.setJobId(userTask.getJobId());
        dto.setShortCmd(userTask.getShortCmd());
        dto.setUserId(userTask.getUserId());
        dto.setSubmittedAt(userTask.getSubmittedAt());
        dto.setStartedAt(userTask.getStartedAt());
        dto.setEndedAt(userTask.getEndedAt());
        dto.setStatus(userTask.getStatus());

        dto.setCommand(userTaskDetails.getCommand());
        dto.setLicenseType(userTaskDetails.getLicenseType());
        dto.setLicenseCount(userTaskDetails.getLicenseCount());
        dto.setDirectory(userTaskDetails.getDirectory());
        dto.setUuid(userTaskDetails.getUuid());
        dto.setTimelimit(userTaskDetails.getTimelimit());
        dto.setRequestedCpu(userTaskDetails.getRequestedCpu());

        return dto;
    }

    public Integer addTask(Task task) {
        return taskMapper.insertTask(task);
    }

    public void updateTask(Task task) {
        taskMapper.updateTask(task);
    }

    public void updateStatus(String status, int id) {
        Task task = taskMapper.findById(id);
        if (task == null) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        if (!isValidStatus(status)) {
            throw new RuntimeException("Invalid status: " + status);
        }
        taskMapper.updateStatus(status, id);
    }

    public void deleteTask(Long id) {
        taskMapper.deleteById(id);
    }


    private boolean isValidStatus(String status) {
        return List.of("Pending", "In Progress", "Completed").contains(status);
    }
}
