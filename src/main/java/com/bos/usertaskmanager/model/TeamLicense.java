package com.bos.usertaskmanager.model;

import lombok.*;

@Getter
@Setter
public class TeamLicense {
    private String teamLicenseId;
    private String teamId;
    private String licenseId;

    public TeamLicense(String teamId, String licenseId) {
        this.teamId = teamId;
        this.licenseId = licenseId;
    }
}
