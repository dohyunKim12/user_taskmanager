package com.bos.usertaskmanager.service;

import com.bos.usertaskmanager.model.TeamLicense;
import com.bos.usertaskmanager.repository.TeamLicenseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamLicenseService {

    @Autowired
    private TeamLicenseMapper teamLicenseMapper;

    public TeamLicense assignLicenseToTeam(String teamId, String licenseId) {
        TeamLicense teamLicense = new TeamLicense(teamId, licenseId);
        teamLicenseMapper.insertTeamLicense(teamLicense);
        return teamLicense;
    }

    public boolean removeLicenseFromTeam(String teamId, String licenseId) {
        return teamLicenseMapper.deleteTeamLicenseByTeamIdAndLicenseId(teamId, licenseId);
    }
}
