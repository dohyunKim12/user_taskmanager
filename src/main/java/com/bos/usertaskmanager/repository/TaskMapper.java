package com.bos.usertaskmanager.repository;

import com.bos.usertaskmanager.model.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskMapper {
    List<Task> findByCompleted(@Param("completed") boolean completed);
    List<Task> findAll();
    List<Task> findByStatus(@Param("status") String status);
    Integer insertTask(Task task);
    void updateTask(Task task);
    void deleteById(@Param("id") Long id);
}
