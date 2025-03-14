package com.bos.usertaskmanager.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/task/add")
    public String showAddTaskForm() {
        return "task-add";
    }

    @GetMapping("/task/add/batch")
    public String showAddTaskBatchForm() {
        return "task-add-batch";
    }

    @GetMapping("/task/list")
    public String showTaskListPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            return "task-list";
    }
}
