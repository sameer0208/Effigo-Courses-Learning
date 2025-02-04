package com.effigoproject.schedulers.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task_execution_logs",schema = "public")
public class TaskExecutionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "execution_time")
    private LocalDateTime executionTime;

    public TaskExecutionEntity(String newTask, LocalDateTime now) {
        this.taskName = newTask;
        this.executionTime = now;
    }
}
