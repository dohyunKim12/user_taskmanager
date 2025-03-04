package com.bos.usertaskmanager.graphql;

import com.bos.usertaskmanager.model.TeamLicense;
import com.bos.usertaskmanager.service.TeamLicenseService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamLicenseResolver implements GraphQLMutationResolver {

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
