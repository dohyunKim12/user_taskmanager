package com.bos.usertaskmanager.service;

import com.bos.usertaskmanager.model.License;
import com.bos.usertaskmanager.repository.LicenseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicenseService {

    @Autowired
    private LicenseMapper licenseMapper;

    public License getLicenseById(String licenseId) {
        return licenseMapper.getLicenseById(licenseId);
    }

    public List<License> getAllLicenses() {
        return licenseMapper.findAllLicenses();
    }

    public License createLicense(License license) {
        licenseMapper.insertLicense(license);
        return license;
    }

    public License updateLicense(License license) {
        licenseMapper.updateLicense(license);
        return license;
    }

    public boolean deleteLicense(String licenseId) {
        return licenseMapper.deleteLicenseById(licenseId) > 0;
    }
}
