package com.bos.usertaskmanager.graphql;

import com.bos.usertaskmanager.model.Team;
import com.bos.usertaskmanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TeamResolver {

    @Autowired
    private TeamService teamService;
//    private final TeamService teamService;
//    public TeamResolver(TeamService teamService) {
//        this.teamService = teamService;
//    }

    @SchemaMapping(typeName = "Query", field = "getTeam")
    public Team getTeam(@Argument String teamId) {
        return teamService.getTeamById(teamId);
    }

    @QueryMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    public Team createTeam(String teamName) {
        Team team = new Team();
        team.setTeamName(teamName);
        return teamService.createTeam(team);
    }

    public Team updateTeam(String teamId, String teamName) {
        Team team = new Team();
        team.setTeamId(teamId);
        team.setTeamName(teamName);
        return teamService.updateTeam(team);
    }

    public Boolean deleteTeam(String teamId) {
        return teamService.deleteTeam(teamId);
    }
}
