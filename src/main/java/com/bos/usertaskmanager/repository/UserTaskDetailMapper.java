package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.model.UserTaskDetail;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserTaskDetailMapper {
    @Insert("""
        INSERT INTO user_task_details 
        (user_task_id, command, license_type, license_count, directory, uuid, timelimit, requested_cpu) 
        VALUES 
        (#{userTaskId}, #{command}, #{licenseType}, #{licenseCount}, 
         #{directory}, #{uuid}, #{timelimit}, #{requestedCpu})
    """)
    void insertUserTaskDetails(UserTaskDetail userTaskDetails);

    @Delete("DELETE FROM user_task_details where user_task_id = #{user_task_id}")
    void deleteUserTaskDetails(String userTaskId);

    @UpdateProvider(type = UserTaskDetailsSqlProvider.class, method = "updateUserTaskDetails")
    void updateUserTaskDetails(UserTaskDetail userTaskDetails);
}
