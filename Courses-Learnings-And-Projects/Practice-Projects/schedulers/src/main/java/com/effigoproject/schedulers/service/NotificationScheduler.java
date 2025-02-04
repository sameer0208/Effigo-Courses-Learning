package com.effigoproject.schedulers.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class NotificationScheduler {

    private static Logger log = LoggerFactory.getLogger(NotificationScheduler.class);


    @Scheduled(fixedRate = 5000) //sending notification after every 5 minutes
    public void setReminder()
    {
        log.info("Reminder sent at: "+ LocalTime.now());
    }

    @Scheduled(cron = "0 0 8 * * ?") //sending notification everyday morning 8 AM
    public void generateDailyReport()
    {
        log.info("Daily report generated at: "+ LocalTime.now());
    }

    @Scheduled(fixedRate = 5000, initialDelay = 10000) // 10 Second delay before first run
    public void delayedTask()
    {
        log.info("Task started after delay: "+LocalTime.now());
    }

    @Scheduled(cron = "0 */10 * * * *") // after every 10 minutes
    public void everyTenMinuteTask()
    {
        System.out.println("Task running every 10 minutes at: "+LocalTime.now());
    }

    public void logging(){
        log.info("testing logging");
    }
}
