package com.effigoproject.schedulers.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class NotificationScheduler {


    @Scheduled(fixedRate = 5000) //sending notification after every 5 minutes
    public void setReminder()
    {
        System.out.println("Reminder sent at: "+ LocalTime.now());
    }

    @Scheduled(cron = "0 0 8 * * ?") //sending notification everyday morning 8 AM
    public void generateDailyReport()
    {
        System.out.println("Daily report generated at: "+ LocalTime.now());
    }

    @Scheduled(fixedRate = 5000, initialDelay = 10000) // 10 Second delay before first run
    public void delayedTask()
    {
        System.out.println("Task started after delay: "+LocalTime.now());
    }

    @Scheduled(cron = "0 */10 * * * *") // after every 10 minutes
    public void everyTenMinuteTask()
    {
        System.out.println("Task running every 10 minutes at: "+LocalTime.now());
    }
}
