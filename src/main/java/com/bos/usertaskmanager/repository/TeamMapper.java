package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.model.Team;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeamMapper {
    @Select("SELECT * FROM team")
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

    @Delete("DELETE FROM team")
    void deleteAllTeams();
}
