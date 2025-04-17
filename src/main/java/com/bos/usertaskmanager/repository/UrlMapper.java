package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.dto.UserDto;
import com.bos.usertaskmanager.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UrlMapper {
    @Insert("INSERT INTO urls (originUrl, short_url) VALUES (#{originUrl}, #{shortUrl})")
    int insertUrl(@Param("originUrl") String originUrl, @Param("shortUrl") String shortUrl);
}
