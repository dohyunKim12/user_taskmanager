package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.model.UserTask;
import com.bos.usertaskmanager.model.UserTaskDetails;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserTaskMapper {
    @Select("SELECT * FROM user_task where user_task_id = #{userTaskId}")
    UserTask getUserTaskById(@Param("userTaskId") String userTaskId);

    @Select("""
        SELECT ut.* FROM user_task ut
        JOIN users u ON ut.user_id = u.user_id
        WHERE u.username = #{username}
    """)
    List<UserTask> getUserTaskListByUsername(String username);

    @Select("SELECT * FROM user_task where status = #{status}")
    List<UserTask> getUserTaskListByStatus(@Param("status") String status);

    @Insert("""
        INSERT INTO user_task (user_id, submitted_at, status) 
        VALUES (#{userTask.userId}, #{userTask.submittedAt}, 'pending') 
        RETURNING user_task_id
    """)
    @Options(useGeneratedKeys = true, keyProperty = "userTask.userTaskId")
    String insertUserTask(@Param("userTask") UserTask userTask);

    @Delete("DELETE FROM user_task where user_task_id = #{user_task_id}")
    void deleteUserTaskById(String userTaskId);


}
