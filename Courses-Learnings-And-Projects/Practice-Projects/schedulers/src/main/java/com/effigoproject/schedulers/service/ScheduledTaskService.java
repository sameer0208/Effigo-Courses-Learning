package com.effigoproject.schedulers.service;

import com.effigoproject.schedulers.entity.TaskExecutionEntity;
import com.effigoproject.schedulers.repository.TaskExecutionRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ScheduledTaskService {
    private TaskExecutionRepository taskExecutionRepository;

    public ScheduledTaskService(TaskExecutionRepository taskExecutionRepository)
    {
        this.taskExecutionRepository = taskExecutionRepository;
    }

    static int count =0;
    @Scheduled(cron = "0 */1 * * * ?")
    public void executeScheduledTask()
    {
        ++count;
        TaskExecutionEntity task = new TaskExecutionEntity("New Task "+count, LocalDateTime.now());
        taskExecutionRepository.save(task);
        System.out.println("Task executed and saved at: "+LocalDateTime.now());
    }
}
