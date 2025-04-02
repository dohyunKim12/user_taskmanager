package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.dto.TaskFilterInput;
import com.bos.usertaskmanager.dto.UserTaskOutDto;
import com.bos.usertaskmanager.model.UserTask;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserTaskMapper {
    // GET
    UserTaskOutDto getUserTaskById(@Param("userTaskId") String userTaskId);

    List<UserTaskOutDto> getAllUserTasks();

    List<UserTaskOutDto> getFilteredUserTasks(TaskFilterInput filterInput);

    @Select("SELECT task_id FROM user_task WHERE user_task_id = #{userTaskId}")
    String getTaskIdByUserTaskId(String userTaskId);

    // INSERT
    @Insert("""
        INSERT INTO user_task (task_id, directory, env, description, exit_code)
        VALUES (
            #{userTask.taskId}, #{userTask.directory},
            #{userTask.env}, #{userTask.description}, #{userTask.exitCode}
        )
    """)
    void insertUserTask(@Param("userTask") UserTask userTask);


    // Delete
    @Delete("""
        DELETE FROM user_task 
        WHERE user_id = (SELECT user_id FROM users WHERE username = #{username})
    """)
    void deleteUserTaskByUsername(String username);

    @Delete("DELETE FROM user_task where user_task_id = #{userTaskId}")
    int deleteUserTaskById(String userTaskId);

    @Delete("DELETE FROM user_task")
    void deleteAllUserTasks();

    @Select("SELECT 'UTK-' || LPAD(nextval('user_task_seq')::text, 6, '0')")
    String getNextUserTaskId();

    @UpdateProvider(type = UserTaskSqlProvider.class, method = "updateUserTask")
    void updateUserTask(UserTask userTask);

}
