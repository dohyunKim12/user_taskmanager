package com.bos.usertaskmanager.graphql;

import com.bos.usertaskmanager.model.License;
import com.bos.usertaskmanager.service.LicenseService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LicenseResolver {
    private final LicenseService licenseService;
    public LicenseResolver(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @QueryMapping
    public License getLicense(@Argument String licenseId) {
        return licenseService.getLicenseById(licenseId);
    }

    @QueryMapping
    public List<License> getAllLicenses() {
        return licenseService.getAllLicenses();
    }

    @MutationMapping
    public License createLicense(@Argument String licenseType, @Argument int maxVal, @Argument int currentVal) {
        License license = new License();
        license.setLicenseType(licenseType);
        license.setMaxVal(maxVal);
        license.setCurrentVal(currentVal);
        return licenseService.createLicense(license);
    }

    @MutationMapping
    public License updateLicense(@Argument String licenseId, @Argument String licenseType, @Argument int maxVal, @Argument int currentVal) {
        License license = new License();
        license.setLicenseId(licenseId);
        license.setLicenseType(licenseType);
        license.setMaxVal(maxVal);
        license.setCurrentVal(currentVal);
        return licenseService.updateLicense(license);
    }

    @MutationMapping
    public Boolean deleteLicenseById(@Argument String licenseId) {
        return licenseService.deleteLicense(licenseId);
    }

    @MutationMapping
    public Boolean deleteAllLicenses() {
        return licenseService.deleteAllLicenses();
    }
}
