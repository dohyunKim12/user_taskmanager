package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.model.UserTask;
import org.apache.ibatis.jdbc.SQL;

public class UserTaskSqlProvider {

    public String updateUserTask(UserTask userTask) {
        return new SQL() {{
            UPDATE("user_task");

            if (userTask.getTaskId() != null) {
                SET("task_id = #{taskId}");
            }
            if (userTask.getDirectory() != null) {
                SET("directory = #{directory}");
            }
            if (userTask.getEnv() != null) {
                SET("env = #{env}");
            }
            if (userTask.getDescription() != null) {
                SET("description = #{description}");
            }
            if (userTask.getExitCode() != null) {
                SET("exit_code = #{exitCode}");
            }

            WHERE("user_task_id = #{userTaskId}");
        }}.toString();
    }
}
