package com.bos.usertaskmanager.graphql;

import com.bos.usertaskmanager.model.License;
import com.bos.usertaskmanager.service.LicenseService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LicenseResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

//    @Autowired
//    private LicenseService licenseService;
    private final LicenseService licenseService;
    public LicenseResolver(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    public License getLicense(String licenseId) {
        return licenseService.getLicenseById(licenseId);
    }

    public List<License> getAllLicenses() {
        return licenseService.getAllLicenses();
    }

    public License createLicense(String licenseType, int maxVal, int currentVal) {
        License license = new License();
        license.setLicenseType(licenseType);
        license.setMaxVal(maxVal);
        license.setCurrentVal(currentVal);
        return licenseService.createLicense(license);
    }

    public License updateLicense(String licenseId, String licenseType, int maxVal, int currentVal) {
        License license = new License();
        license.setLicenseId(licenseId);
        license.setLicenseType(licenseType);
        license.setMaxVal(maxVal);
        license.setCurrentVal(currentVal);
        return licenseService.updateLicense(license);
    }

    public Boolean deleteLicense(String licenseId) {
        return licenseService.deleteLicense(licenseId);
    }
}
