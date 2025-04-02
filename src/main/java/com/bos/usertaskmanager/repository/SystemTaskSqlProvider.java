package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.model.SystemTask;
import org.apache.ibatis.jdbc.SQL;

public class SystemTaskSqlProvider {

    public String updateSystemTask(SystemTask systemTask) {
        return new SQL() {{
            UPDATE("system_task");

            if (systemTask.getTaskId() != null) {
                SET("task_id = #{taskId}");
            }
            if (systemTask.getSystemType() != null) {
                SET("system_type = #{systemType}");
            }
            if (systemTask.getConfig() != null) {
                SET("config = #{config}");
            }

            WHERE("system_task_id = #{systemTaskId}");
        }}.toString();
    }
}
