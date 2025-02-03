package com.effigoproject.schedulers.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DynamicScheduler {
    private boolean isSchedulerActive = true;

    @Scheduled(fixedRate = 5000)
    public void runTask()
    {
        if(isSchedulerActive)
        {
            System.out.println("Scheduled Task Running.......");
        }
        else
        {
            System.out.println("Scheduler is disabled");
        }
    }

    public void enableScheduler()
    {
        isSchedulerActive = true;
    }

    public void disableScheduler()
    {
        isSchedulerActive = false;
    }
}
