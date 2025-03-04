package com.bos.usertaskmanager.graphql;

import com.bos.usertaskmanager.model.Team;
import com.bos.usertaskmanager.service.TeamService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

//    @Autowired
//    private TeamService teamService;
    private final TeamService teamService;
    public TeamResolver(TeamService teamService) {
        this.teamService = teamService;
    }

    public Team getTeam(String teamId) {
        return teamService.getTeamById(teamId);
    }

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
