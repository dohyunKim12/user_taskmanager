package com.bos.usertaskmanager.graphql;

import com.bos.usertaskmanager.dto.LicenseInput;
import com.bos.usertaskmanager.dto.ResultDto;
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
    public License createLicense(@Argument LicenseInput input) {
        License license = new License();
        license.setLicenseType(input.getLicenseType());
        license.setMaxVal(input.getMaxVal());
        license.setCurrentVal(input.getCurrentVal());
        return licenseService.createLicense(license);
    }

    @MutationMapping
    public License updateLicense(@Argument LicenseInput input) {
        License license = new License();
        license.setLicenseId(input.getLicenseId());
        license.setLicenseType(input.getLicenseType());
        license.setMaxVal(input.getMaxVal());
        license.setCurrentVal(input.getCurrentVal());
        return licenseService.updateLicense(license);
    }

    @MutationMapping
    public ResultDto deleteLicenseById(@Argument String licenseId) {
        if(licenseService.deleteLicense(licenseId)){
            return new ResultDto(true, "License deleted successfully");
        } else {
            return new ResultDto(false, "License not found");
        }
    }

    @MutationMapping
    public ResultDto deleteAllLicenses() {
        if(licenseService.deleteAllLicenses()){
            return new ResultDto(true, "All licenses deleted successfully");
        } else {
            return new ResultDto(false, "No licenses found");
        }
    }
}
