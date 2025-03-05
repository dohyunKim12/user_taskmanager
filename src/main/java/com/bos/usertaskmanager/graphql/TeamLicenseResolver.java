package com.bos.usertaskmanager.graphql;

import com.bos.usertaskmanager.model.TeamLicense;
import com.bos.usertaskmanager.service.TeamLicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class TeamLicenseResolver {

//    @Autowired
//    private TeamLicenseService teamLicenseService;
    private final TeamLicenseService teamLicenseService;
    public TeamLicenseResolver(TeamLicenseService teamLicenseService) {
        this.teamLicenseService = teamLicenseService;
    }

    public TeamLicense assignLicenseToTeam(String teamId, String licenseId) {
        return teamLicenseService.assignLicenseToTeam(teamId, licenseId);
    }

    public Boolean removeLicenseFromTeam(String teamId, String licenseId) {
        return teamLicenseService.removeLicenseFromTeam(teamId, licenseId);
    }
}
