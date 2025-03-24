package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.model.License;
import com.bos.usertaskmanager.model.Team;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LicenseMapper {
    @Select("SELECT * FROM license")
    List<License> findAllLicenses();

    @Select("SELECT * FROM license where license_id = #{id}")
    License getLicenseById(String id);

    @Select("SELECT * FROM license where license_type = #{licenseType}")
    License getLicenseByLicenseType(String licenseType);

    @Insert("INSERT INTO license (license_type, max_val, current_val) values (#{licenseType}, #{maxVal}, #{currentVal})")
    void insertLicense(License license);

    @Update("UPDATE license SET license_type = #{licenseType}, max_val = #{maxVal}, current_val = #{currentVal} WHERE license_id = #{id}")
    void updateLicense(License license);

    @Delete("DELETE FROM license where license_type = #{licenseType}")
    void deleteLicenseByLicenseType(String licenseType);

    @Delete("DELETE FROM license where license_id = #{id}")
    int deleteLicenseById(String id);

    @Delete("DELETE FROM license")
    int deleteAllLicenses();
}
