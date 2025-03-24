package com.bos.usertaskmanager.graphql;

import com.bos.usertaskmanager.dto.ResultDto;
import com.bos.usertaskmanager.dto.TeamDto;
import com.bos.usertaskmanager.model.Team;
import com.bos.usertaskmanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TeamResolver {
    private final TeamService teamService;
    public TeamResolver(TeamService teamService) {
        this.teamService = teamService;
    }

    @QueryMapping
    public Team getTeam(@Argument String teamId) {
        return teamService.getTeamById(teamId);
    }

    @QueryMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @MutationMapping
    public Team createTeam(@Argument String teamName) {
        Team team = new Team();
        team.setTeamName(teamName);
        return teamService.createTeam(team);
    }

    @MutationMapping
    public Team updateTeam(@Argument String teamId,@Argument String teamName) {
        Team team = new Team();
        team.setTeamId(teamId);
        team.setTeamName(teamName);
        return teamService.updateTeam(team);
    }

    @MutationMapping
    public ResultDto updateTeamLicense(@Argument String teamId,@Argument List<String> licenseTypes) {
        TeamDto teamDto = new TeamDto();
        teamDto.setTeamId(teamId);
        teamDto.setLicenseTypes(licenseTypes);
        if(teamService.updateTeamLicense(teamDto)) {
            return new ResultDto(true, "Team license updated successfully");
        } else {
            return new ResultDto(false, "Team not found");
        }
    }

    @MutationMapping
    public ResultDto deleteTeam(@Argument String teamId) {
        if(teamService.deleteTeam(teamId)) {
            return new ResultDto(true, "Team deleted successfully");
        } else {
            return new ResultDto(false, "Team not found");
        }
    }
}
