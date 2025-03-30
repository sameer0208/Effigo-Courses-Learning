package com.effigoproject.schedulers.component;

import com.effigoproject.schedulers.service.NotificationScheduler;
import org.springframework.scheduling.annotation.Scheduled;

public class CronScheduler {

    private NotificationScheduler notificationScheduler;

    public CronScheduler(NotificationScheduler notificationScheduler)
    {
        this.notificationScheduler = notificationScheduler;
    }

    @Scheduled(cron = "*/20 * * * * *", zone = "Asia/Kolkata")
    public void loggingCronScheduler(){
        notificationScheduler.logging();
    }
}
