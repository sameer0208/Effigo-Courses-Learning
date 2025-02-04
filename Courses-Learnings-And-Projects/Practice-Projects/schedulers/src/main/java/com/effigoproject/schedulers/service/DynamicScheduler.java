package com.effigoproject.schedulers.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DynamicScheduler {

    private static Logger log = LoggerFactory.getLogger(DynamicScheduler.class);

    private boolean isSchedulerActive = true;

    @Scheduled(fixedRate = 5000)
    public void runTask()
    {
        if(isSchedulerActive)
        {
            log.info("Scheduled Task Running.......");
        }
        else
        {
            log.warn("Scheduler is disabled");
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
