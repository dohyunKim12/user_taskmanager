package com.bos.usertaskmanager.repository;

import org.apache.ibatis.jdbc.SQL;

import com.bos.usertaskmanager.model.Task;

public class TaskSqlProvider {
    public String updateTaskFields(Task task) {
        return new SQL() {{
            UPDATE("task");

            if (task.getUserId() != null) {
                SET("user_id = #{userId}");
            }
            if (task.getLicenseType() != null) {
                SET("license_type = #{licenseType}");
            }
            if (task.getLicenseCount() != null) {
                SET("license_count = #{licenseCount}");
            }
            if (task.getTimelimit() != null) {
                SET("timelimit = #{timelimit}");
            }
            if (task.getRequestedCpu() != null) {
                SET("requested_cpu = #{requestedCpu}");
            }
            if (task.getRequestedMem() != null) {
                SET("requested_mem = #{requestedMem}");
            }
            if (task.getPartition() != null) {
                SET("partition = #{partition}");
            }
            if (task.getPriority() != null) {
                SET("priority = #{priority}");
            }

            WHERE("task_id = #{taskId}");
        }}.toString();
    }
}

