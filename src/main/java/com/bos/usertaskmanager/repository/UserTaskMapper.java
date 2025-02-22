package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.model.UserTask;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserTaskMapper {
    // GET
    @Select("SELECT * FROM user_task")
    List<UserTask> getAllUserTasks();

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

    @Select("""
        SELECT ut.* FROM user_task ut
        JOIN user_task_details utd ON ut.user_task_id = utd.user_task_id
        WHERE utd.license_type = #{licenseType}
    """)
    List<UserTask> getUserTaskListByLicenseType(@Param("licenseType") String licenseType);

    // get user task from submitted_at to
    @Select("""
        SELECT * FROM user_task 
        WHERE submitted_at >= #{from} AND submitted_at <= #{to}
    """)
    List<UserTask> getUserTaskListBySubmittedAt(@Param("from") String from, @Param("to") String to);


    // INSERT
    @Insert("""
        INSERT INTO user_task (user_id, submitted_at, status) 
        VALUES (#{userTask.userId}, #{userTask.submittedAt}, 'pending') 
        RETURNING user_task_id
    """)
    @Options(useGeneratedKeys = true, keyProperty = "userTask.userTaskId")
    String insertUserTask(@Param("userTask") UserTask userTask);

    @Delete("DELETE FROM user_task where user_task_id = #{user_task_id}")
    void deleteUserTaskById(String userTaskId);

    @Delete("DELETE FROM user_task")
    void deleteAllUserTasks();

    // Delete task by username
    @Delete("""
        DELETE FROM user_task 
        WHERE user_id = (SELECT user_id FROM users WHERE username = #{username})
    """)
    void deleteUserTaskByUsername(String username);

    // UPDATE
    @Update("UPDATE user_task SET status = #{status} WHERE user_task_id = #{user_task_id}")
    void updateUserTaskStatus(@Param("status") String status, @Param("user_task_id") String userTaskId);

    @Update("UPDATE user_task SET started_at = #{startedAt} WHERE user_task_id = #{user_task_id}")
    void updateUserTaskStartedAt(@Param("startedAt") String startedAt, @Param("user_task_id") String userTaskId);

    @Update("UPDATE user_task SET ended_at = #{endedAt} WHERE user_task_id = #{user_task_id}")
    void updateUserTaskEndedAt(@Param("endedAt") String endedAt, @Param("user_task_id") String userTaskId);

    @Update("UPDATE user_task SET job_id = #{jobId} WHERE user_task_id = #{user_task_id}")
    void updateUserTaskJobId(@Param("jobId") String jobId, @Param("user_task_id") String userTaskId);
}
