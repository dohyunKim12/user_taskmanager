package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.dto.TaskFilterInput;
import com.bos.usertaskmanager.dto.UserTaskDto;
import com.bos.usertaskmanager.model.Task;
import com.bos.usertaskmanager.model.UserTask;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserTaskMapper {
    // GET
    @Select("""
        SELECT 
            t.*, ut.user_task_id, ut.directory, ut.env, ut.description, ut.exit_code
        FROM user_task ut
        JOIN task t ON ut.task_id = t.task_id
        WHERE ut.user_task_id = #{userTaskId}
    """)
    UserTaskDto getUserTaskById(@Param("userTaskId") String userTaskId);

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
    String getNextTaskId();

    @UpdateProvider(type = UserTaskSqlProvider.class, method = "updateUserTask")
    void updateUserTask(UserTask userTask);

    // In Mapper.xml
    List<UserTaskDto> getAllUserTasks();

    List<UserTask> getFilteredUserTasks(TaskFilterInput filterInput);
}
