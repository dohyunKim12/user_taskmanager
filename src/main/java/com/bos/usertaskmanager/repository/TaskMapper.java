package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.model.Task;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface TaskMapper {

    // SELECT
    @Select("SELECT * FROM task")
    List<Task> findAll();

    @Select("SELECT * FROM task WHERE task_id = #{taskId}")
    Task getTaskById(@Param("taskId") String taskId);

    @Select("SELECT * FROM task WHERE user_id = #{userId}")
    List<Task> getTasksByUserId(@Param("userId") String userId);

    @Select("SELECT * FROM task WHERE status = #{status}")
    List<Task> getTasksByStatus(@Param("status") String status);

    @Select("""
        SELECT * FROM task 
        WHERE submitted_at >= #{from} AND submitted_at <= #{to}
    """)
    List<Task> getTasksBySubmittedRange(@Param("from") String from, @Param("to") String to);

    // INSERT
    @Insert("""
        INSERT INTO task (
            task_id, task_type, user_id, requested_cpu, requested_mem, license_type,
            license_count, timelimit, command, partition, status, priority,
            submitted_at, started_at, ended_at
        ) VALUES (
            #{task.taskId}, #{task.taskType}, #{task.userId}, #{task.requestedCpu}, #{task.requestedMem},
            #{task.licenseType}, #{task.licenseCount}, #{task.timelimit}, #{task.command},
            #{task.partition}, #{task.status}, #{task.priority},
            #{task.submittedAt}, #{task.startedAt}, #{task.endedAt}
        )
    """)
    void insertTask(@Param("task") Task task);

    // UPDATE
    @Update("""
        UPDATE task SET
            status = COALESCE(#{task.status}, status),
            started_at = COALESCE(#{task.startedAt}, started_at),
            ended_at = COALESCE(#{task.endedAt}, ended_at)
        WHERE task_id = #{task.taskId}
    """)
    void updateTaskStatus(@Param("task") Task task);

    @UpdateProvider(type = TaskSqlProvider.class, method = "updateTaskFields")
    void updateTaskFields(Task task);

    // DELETE
    @Delete("DELETE FROM task WHERE task_id = #{taskId}")
    int deleteTaskById(String taskId);

    @Delete("DELETE FROM task")
    void deleteAllTasks();

    @Select("SELECT 'TSK-' || LPAD(nextval('task_seq')::text, 6, '0')")
    String getNextTaskId();
}
