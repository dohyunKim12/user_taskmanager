//package com.bos.usertaskmanager.scheduler;
//
//import com.bos.usertaskmanager.model.Task;
//import com.bos.usertaskmanager.service.TaskService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class TaskScheduler {
//
//    private final TaskService taskService;
//
//    @Scheduled(fixedRate = 5000) // 5초마다 실행
//    public void processTasks() {
//        List<Task> tasks = taskService.getAllTasks();
//        if (!tasks.isEmpty()) {
//            Task task = tasks.get(0);
//            System.out.println("Processing task: " + task.getDescription());
//            taskService.updateStatus("completed", Math.toIntExact(task.getId()));
//            taskService.updateTask(task);
//        }
//    }
//}
