package com.bos.usertaskmanager.service;

import com.bos.usertaskmanager.model.License;
import com.bos.usertaskmanager.repository.LicenseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * LicenseService is a service class that provides methods to manage licenses.
 * It uses LicenseMapper to interact with the database.
 * <p>
 * The @Service annotation indicates that this class is a service component in the Spring context.
 * The @RequiredArgsConstructor annotation generates a constructor with required arguments (final fields).
 * The @Transactional annotation indicates that methods in this class should be executed within a transaction.
 * </p>
 *
 * @author dohyun.kim
 */
@Service
@RequiredArgsConstructor
@Transactional
public class LicenseService {
    private final LicenseMapper licenseMapper;

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

    public boolean deleteAllLicenses() {
        return licenseMapper.deleteAllLicenses() > 0;
    }
}
