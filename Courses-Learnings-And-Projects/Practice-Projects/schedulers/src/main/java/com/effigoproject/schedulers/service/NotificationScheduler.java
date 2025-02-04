package com.effigoproject.schedulers.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class NotificationScheduler {

    private static Logger log = LoggerFactory.getLogger(NotificationScheduler.class);


    public void setReminder()
    {
        log.info("Reminder sent at: "+ LocalTime.now());
    }

    public void delayedTask()
    {
        log.info("Task started after delay: "+LocalTime.now());
    }

    public void logging(){
        log.info("testing logging");
    }
}
