package com.bos.usertaskmanager.service;

import com.bos.usertaskmanager.dto.TeamDto;
import com.bos.usertaskmanager.model.License;
import com.bos.usertaskmanager.model.Team;
import com.bos.usertaskmanager.model.TeamLicense;
import com.bos.usertaskmanager.repository.LicenseMapper;
import com.bos.usertaskmanager.repository.TeamLicenseMapper;
import com.bos.usertaskmanager.repository.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TeamService {
    private final TeamMapper teamMapper;
    private final LicenseMapper licenseMapper;
    private final TeamLicenseMapper teamLicenseMapper;

    public TeamService(TeamMapper teamMapper, LicenseMapper licenseMapper, TeamLicenseMapper teamLicenseMapper) {
        this.teamMapper = teamMapper;
        this.licenseMapper = licenseMapper;
        this.teamLicenseMapper = teamLicenseMapper;
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

    public boolean updateTeamLicense(TeamDto teamDto) {
        try {
            teamLicenseMapper.deleteTeamLicenseByTeamId(teamDto.getTeamId());
            String teamId = teamDto.getTeamId();

            List<String> licenseTypes = teamDto.getLicenseTypes();
            for (String licenseType : licenseTypes) {
                License license = licenseMapper.getLicenseByLicenseType(licenseType);
                TeamLicense teamLicense = new TeamLicense(teamId, license.getLicenseId());
                teamLicenseMapper.insertTeamLicense(teamLicense);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteTeam(String teamId) {
        return teamMapper.deleteTeamById(teamId) > 0;
    }

    public TeamLicense assignLicenseToTeam(String teamId, String licenseId) {
        TeamLicense teamLicense = new TeamLicense(teamId, licenseId);
        teamLicenseMapper.insertTeamLicense(teamLicense);
        return teamLicense;
    }

    public boolean removeLicenseFromTeam(String teamId, String licenseId) {
        return teamLicenseMapper.deleteTeamLicenseByTeamIdAndLicenseId(teamId, licenseId);
    }
}
