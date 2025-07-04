package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.dto.TeamDto;
import com.bos.usertaskmanager.model.Team;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeamMapper {
    @Select("SELECT * FROM team")
    @Results({
            @Result(property = "teamId", column = "team_id"),
            @Result(property = "teamName", column = "team_name")
    })
    List<Team> getAllTeams();

    @Select("SELECT * FROM team where team_id = #{id}")
    Team getTeamById(String id);

    @Select("SELECT * FROM team where team_name = #{teamName}")
    Team getTeamByTeamName(String teamName);

    @Insert("INSERT INTO team (team_name) values (#{teamName})")
    void insertTeam(Team team);

    @Update("UPDATE team SET team_name = #{teamName} WHERE team_id = #{id}")
    void updateTeam(Team team);

    @Delete("DELETE FROM team where team_name = #{teamName}")
    void deleteTeamByTeamName(String teamName);

    @Delete("DELETE FROM team where team_id = #{id}")
    int deleteTeamById(String id);

    @Update("UPDATE team_license SET license_id = (SELECT license_id FROM license WHERE license_type = #{licenseType}) WHERE team_id = #{teamId}")
    boolean updateTeamLicense(TeamDto teamDto);

    @Delete("DELETE FROM team")
    void deleteAllTeams();

    @Select("SELECT license_type FROM license left join team_license " +
            "on license.license_id = team_license.license_id where team_id = #{teamId}")
    List<String> getLicenseTypesByTeamId(String teamId);
}
