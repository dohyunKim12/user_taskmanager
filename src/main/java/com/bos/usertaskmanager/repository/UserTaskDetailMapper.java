package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.model.UserTaskDetail;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserTaskDetailMapper {
    @Insert("""
        INSERT INTO user_task_detail 
        (user_task_id, command, partition, license_type, license_count, directory, uuid, timelimit, requested_cpu, requested_mem) 
        VALUES 
        (#{userTaskId}, #{command}, #{partition}, #{licenseType}, #{licenseCount}, 
         #{directory}, #{uuid}, #{timelimit}, #{requestedCpu}, #{requestedMem})
    """)
    void insertUserTaskDetail(UserTaskDetail userTaskDetail);

    @Select("SELECT * FROM user_task_detail where user_task_id = #{user_task_id}")
    UserTaskDetail getUserTaskDetailById(String userTaskId);

    @Delete("DELETE FROM user_task_detail where user_task_id = #{user_task_id}")
    void deleteUserTaskDetail(String userTaskId);

    @UpdateProvider(type = UserTaskDetailSqlProvider.class, method = "updateUserTaskDetail")
    void updateUserTaskDetail(UserTaskDetail userTaskDetail);
}
