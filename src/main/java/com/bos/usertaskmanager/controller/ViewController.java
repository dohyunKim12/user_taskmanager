package com.bos.usertaskmanager.controller;

import com.bos.usertaskmanager.config.UtmdProperties;
import com.bos.usertaskmanager.service.LicenseService;
import com.bos.usertaskmanager.service.TeamService;
import com.bos.usertaskmanager.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    private final UserService userService;
    private final TeamService teamService;
    private final LicenseService licenseService;
    private final UtmdProperties utmdProperties;

    public ViewController(UserService userService, TeamService teamService, LicenseService licenseService, UtmdProperties utmdProperties) {
        this.userService = userService;
        this.teamService = teamService;
        this.licenseService = licenseService;
        this.utmdProperties = utmdProperties;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to User Task Manager");
        return "index";
    }

    @GetMapping("/task/add")
    public String showAddTaskForm() {
        return "task-add";
    }

    @GetMapping("/task/add/batch")
    public String showAddTaskBatchForm(Model model) {
        if (utmdProperties != null && utmdProperties.getIpPortList() != null && !utmdProperties.getIpPortList().isEmpty()) {
            model.addAttribute("utmdAgentIpPort", utmdProperties.getIpPortList().get(0));
        } else {
            model.addAttribute("utmdAgentIpPort", "not configured");
        }
        return "task-add-batch";
    }

    @GetMapping("/task/list")
    public String showTaskListPage() {
        return "task-list";
    }

    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        model.addAttribute("users", userService.getAllUsersInfoExceptAdmin());
        model.addAttribute("teams", teamService.getAllTeamsInfo());
        model.addAttribute("licenses", licenseService.getAllLicenses());
        return "admin";
    }
}
