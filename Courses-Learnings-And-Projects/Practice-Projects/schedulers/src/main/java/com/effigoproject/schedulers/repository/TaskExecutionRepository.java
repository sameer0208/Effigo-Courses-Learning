package com.effigoproject.schedulers.repository;

import com.effigoproject.schedulers.entity.TaskExecutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskExecutionRepository extends JpaRepository<TaskExecutionEntity, String> {

}
