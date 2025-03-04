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

    @Select("SELECT * FROM license where license_name = #{licenseName}")
    License getLicenseByLicenseName(String licenseName);

    @Insert("INSERT INTO license (license_name, max_val, current_val) values (#{licenseName}, #{maxVal}, #{currentVal})")
    void insertLicense(License license);

    @Update("UPDATE license SET license_name = #{licenseName}, max_val = #{maxVal}, current_val = #{currentVal} WHERE license_id = #{id}")
    void updateLicense(License license);

    @Delete("DELETE FROM license where license_name = #{licenseName}")
    void deleteLicenseByLicenseName(String licenseName);

    @Delete("DELETE FROM license where license_id = #{id}")
    int deleteLicenseById(String id);

    @Delete("DELETE FROM license")
    void deleteAllLicenses();
}
