package com.bos.usertaskmanager.service;

import com.bos.usertaskmanager.model.Team;
import com.bos.usertaskmanager.repository.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamMapper teamMapper;

    public Team getTeamById(String teamId) {
        return teamMapper.getTeamById(teamId);
    }

    public List<Team> getAllTeams() {
        return teamMapper.getAllTeams();
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
