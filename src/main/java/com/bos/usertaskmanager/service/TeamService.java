package com.bos.usertaskmanager.service;

import com.bos.usertaskmanager.dto.TeamDto;
import com.bos.usertaskmanager.model.Team;
import com.bos.usertaskmanager.repository.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {
    private final TeamMapper teamMapper;
    public TeamService(TeamMapper teamMapper) {
        this.teamMapper = teamMapper;
    }

    public Team getTeamById(String teamId) {
        return teamMapper.getTeamById(teamId);
    }

    public List<Team> getAllTeams() {
        return teamMapper.getAllTeams();
    }

    public List<TeamDto> getAllTeamsInfo() {
        List<Team> teams = teamMapper.getAllTeams();
        List<TeamDto> teamDtos = new ArrayList<>();
        for(Team team : teams) {
            List<String> licenseTypes = teamMapper.getLicenseTypesByTeamId(team.getTeamId());
            teamDtos.add(new TeamDto(team.getTeamId(), team.getTeamName(), licenseTypes));
        }
        return teamDtos;
    }

    public Team createTeam(Team team) {
        teamMapper.insertTeam(team);
        return team;
    }

    public Team updateTeam(Team team) {
        teamMapper.updateTeam(team);
        return team;
    }

    public boolean deleteTeam(String teamId) {
        return teamMapper.deleteTeamById(teamId) > 0;
    }
}
