package com.bos.usertaskmanager.graphql;

import com.bos.usertaskmanager.model.TeamLicense;
import com.bos.usertaskmanager.service.TeamLicenseService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class TeamLicenseResolver {
    private final TeamLicenseService teamLicenseService;
    public TeamLicenseResolver(TeamLicenseService teamLicenseService) {
        this.teamLicenseService = teamLicenseService;
    }

    @MutationMapping
    public TeamLicense assignLicenseToTeam(@Argument String teamId, @Argument String licenseId) {
        return teamLicenseService.assignLicenseToTeam(teamId, licenseId);
    }

    @MutationMapping
    public Boolean removeLicenseFromTeam(@Argument String teamId, @Argument String licenseId) {
        return teamLicenseService.removeLicenseFromTeam(teamId, licenseId);
    }
}
