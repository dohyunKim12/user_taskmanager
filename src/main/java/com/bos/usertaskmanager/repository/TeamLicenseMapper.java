package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.model.License;
import com.bos.usertaskmanager.model.TeamLicense;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeamLicenseMapper {
    @Select("SELECT * FROM team_license")
    List<TeamLicense> findAllTeamLicenses();

    @Select("SELECT * FROM team_license where team_license_id = #{id}")
    TeamLicense getTeamLicenseById(String id);

    @Select("SELECT * FROM team_license where team_id = #{teamId}")
    TeamLicense getTeamLicenseByTeamId(String teamId);

    @Select("SELECT * FROM team_license where license_id = #{licenseId}")
    TeamLicense getTeamLicenseByLicenseId(String licenseId);

    @Insert("INSERT INTO team_license (team_id, license_id) values (#{teamId}, #{licenseId})")
    void insertTeamLicense(TeamLicense teamLicense);

    @Update("UPDATE team_license SET team_id = #{teamId}, license_id = #{licenseId} WHERE team_license_id = #{id}")
    void updateTeamLicense(TeamLicense teamLicense);

    @Delete("DELETE FROM team_license where team_id = #{teamId}")
    void deleteTeamLicenseByTeamId(String teamId);

    @Delete("DELETE FROM team_license where license_id = #{licenseId}")
    void deleteTeamLicenseByLicenseId(String licenseId);

    @Delete("DELETE FROM team_license where team_license_id = #{id}")
    int deleteTeamLicenseById(String id);

    @Delete("DELETE FROM team_license where team_id = #{teamId} and license_id = #{licenseId}")
    boolean deleteTeamLicenseByTeamIdAndLicenseId(String teamId, String licenseId);

    @Delete("DELETE FROM team_license")
    void deleteAllTeamLicenses();
}
