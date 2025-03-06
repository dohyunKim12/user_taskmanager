package com.bos.usertaskmanager.service;

import com.bos.usertaskmanager.model.TeamLicense;
import com.bos.usertaskmanager.repository.TeamLicenseMapper;
import org.springframework.stereotype.Service;

@Service
public class TeamLicenseService {
    private final TeamLicenseMapper teamLicenseMapper;
    public TeamLicenseService(TeamLicenseMapper teamLicenseMapper) {
        this.teamLicenseMapper = teamLicenseMapper;
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
