package com.bos.usertaskmanager.controller;

import com.bos.usertaskmanager.dto.ResultDto;
import com.bos.usertaskmanager.dto.TeamDto;
import com.bos.usertaskmanager.model.Team;
import com.bos.usertaskmanager.model.TeamLicense;
import com.bos.usertaskmanager.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TeamController {
    private final TeamService teamService;
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/team")
    public Team getTeamRest(@RequestParam("teamId") String teamId) {
        return teamService.getTeamById(teamId);
    }

    @GetMapping("/teams")
    public List<Team> getAllTeamsRest() {
        return teamService.getAllTeams();
    }

    @PostMapping("/createTeam")
    public Team createTeamRest(@RequestBody Map<String, String> request) {
        String teamName = request.get("teamName");
        Team team = new Team();
        team.setTeamName(teamName);
        return teamService.createTeam(team);
    }

    @PutMapping("/updateTeam")
    public Team updateTeamRest(@RequestBody Map<String, String> request) {
        String teamId = request.get("teamId");
        String teamName = request.get("teamName");
        Team team = new Team();
        team.setTeamId(teamId);
        team.setTeamName(teamName);
        return teamService.updateTeam(team);
    }

    @PostMapping("/updateTeamLicense")
    public ResultDto updateTeamLicenseRest(@RequestBody TeamDto teamDto) {
        if (teamService.updateTeamLicense(teamDto)) {
            return new ResultDto(true, "Team license updated successfully");
        } else {
            return new ResultDto(false, "Team not found");
        }
    }

    @DeleteMapping("/deleteTeam/{teamId}")
    public ResultDto deleteTeamRest(@PathVariable String teamId) {
        if (teamService.deleteTeam(teamId)) {
            return new ResultDto(true, "Team deleted successfully");
        } else {
            return new ResultDto(false, "Team not found");
        }
    }

    @PostMapping("/assignLicenseToTeam")
    public TeamLicense assignLicenseToTeamRest(@RequestBody Map<String, String> request) {
        String teamId = request.get("teamId");
        String licenseId = request.get("licenseId");
        return teamService.assignLicenseToTeam(teamId, licenseId);
    }

    @DeleteMapping("/removeLicenseFromTeam/{teamId}/{licenseId}")
    public ResultDto removeLicenseFromTeamRest(@PathVariable String teamId, @PathVariable String licenseId) {
        if (teamService.removeLicenseFromTeam(teamId, licenseId)) {
            return new ResultDto(true, "License removed from team successfully");
        } else {
            return new ResultDto(false, "License not found in team");
        }
    }
}
