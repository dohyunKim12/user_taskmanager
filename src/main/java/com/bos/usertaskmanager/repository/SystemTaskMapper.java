package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.dto.SystemTaskDto;
import com.bos.usertaskmanager.model.SystemTask;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SystemTaskMapper {

    @Select("""
        SELECT 
            t.*, st.system_task_id, st.system_type, st.config
        FROM system_task st
        JOIN task t ON st.task_id = t.task_id
    """)
    List<SystemTaskDto> findAllSystemTask();

    @Select("""
        SELECT 
            t.*, st.system_task_id, st.system_type, st.config
        FROM system_task st
        JOIN task t ON st.task_id = t.task_id
        WHERE st.system_task_id = #{systemTaskId}
    """)
    SystemTaskDto getSystemTaskById(@Param("systemTaskId") String systemTaskId);

    @Insert("""
        INSERT INTO system_task (system_task_id, task_id, system_type, config)
        VALUES (#{systemTask.systemTaskId}, #{systemTask.taskId}, #{systemTask.systemType}, #{systemTask.config})
    """)
    void insertSystemTask(@Param("systemTask") SystemTask systemTask);

    @UpdateProvider(type = SystemTaskSqlProvider.class, method = "updateSystemTask")
    void updateSystemTask(SystemTask systemTask);

    @Delete("DELETE FROM system_task WHERE system_task_id = #{systemTaskId}")
    int deleteSystemTaskById(String systemTaskId);

    @Delete("DELETE FROM system_task")
    void deleteAllSystemTasks();

    @Select("SELECT 'STK-' || LPAD(nextval('system_task_seq')::text, 6, '0')")
    String getNextSystemTaskId();
}
