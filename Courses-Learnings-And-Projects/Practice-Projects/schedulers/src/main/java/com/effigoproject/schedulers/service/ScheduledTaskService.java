package com.effigoproject.schedulers.service;

import com.effigoproject.schedulers.entity.TaskExecutionEntity;
import com.effigoproject.schedulers.repository.TaskExecutionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ScheduledTaskService {

    private static Logger log = LoggerFactory.getLogger(ScheduledTaskService.class);

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
        log.info("Task executed and saved at: "+LocalDateTime.now());
    }
}
