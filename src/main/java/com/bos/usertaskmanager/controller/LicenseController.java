package com.bos.usertaskmanager.controller;

import com.bos.usertaskmanager.dto.LicenseInput;
import com.bos.usertaskmanager.dto.ResultDto;
import com.bos.usertaskmanager.model.License;
import com.bos.usertaskmanager.service.LicenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LicenseController {
    private final LicenseService licenseService;
    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping("/license")
    public License getLicenseRest(@RequestParam("licenseId") String licenseId) {
        return licenseService.getLicenseById(licenseId);
    }

    @GetMapping("/licenses")
    public List<License> getAllLicensesRest() {
        return licenseService.getAllLicenses();
    }

    @PostMapping("/createLicense")
    public License createLicenseRest(@RequestBody LicenseInput input) {
        License license = new License();
        license.setLicenseType(input.getLicenseType());
        license.setMaxVal(input.getMaxVal());
        license.setCurrentVal(input.getCurrentVal());
        return licenseService.createLicense(license);
    }

    @PutMapping("/updateLicense")
    public License updateLicenseRest(@RequestBody LicenseInput input) {
        License license = new License();
        license.setLicenseId(input.getLicenseId());
        license.setLicenseType(input.getLicenseType());
        license.setMaxVal(input.getMaxVal());
        license.setCurrentVal(input.getCurrentVal());
        return licenseService.updateLicense(license);
    }

    @DeleteMapping("/deleteLicense/{licenseId}")
    public ResultDto deleteLicenseByIdRest(@PathVariable String licenseId) {
        if(licenseService.deleteLicense(licenseId)){
            return new ResultDto(true, "License deleted successfully");
        } else {
            return new ResultDto(false, "License not found");
        }
    }

    @DeleteMapping("/deleteLicenses")
    public ResultDto deleteAllLicensesRest() {
        if(licenseService.deleteAllLicenses()){
            return new ResultDto(true, "All licenses deleted successfully");
        } else {
            return new ResultDto(false, "No licenses found");
        }
    }
}
