package com.bos.usertaskmanager.repository;

import org.apache.ibatis.jdbc.SQL;
import com.bos.usertaskmanager.model.UserTaskDetail;

public class UserTaskDetailSqlProvider {
    public String updateUserTaskDetail(UserTaskDetail details) {
        return new SQL() {{
            UPDATE("user_task_detail");
            if (details.getCommand() != null) {
                SET("command = #{command}");
            }
            if (details.getLicenseType() != null) {
                SET("license_type = #{licenseType}");
            }
            if (details.getLicenseCount() != null) {
                SET("license_count = #{licenseCount}");
            }
            if (details.getDirectory() != null) {
                SET("directory = #{directory}");
            }
            if (details.getUuid() != null) {
                SET("uuid = #{uuid}");
            }
            if (details.getTimelimit() != null) {
                SET("timelimit = #{timelimit}");
            }
            if (details.getRequestedCpu() != null) {
                SET("requested_cpu = #{requestedCpu}");
            }
            WHERE("user_task_id = #{userTaskId}");
        }}.toString();
    }
}
