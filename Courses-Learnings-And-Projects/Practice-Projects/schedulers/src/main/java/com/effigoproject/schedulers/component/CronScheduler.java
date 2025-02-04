package com.effigoproject.schedulers.component;

import ch.qos.logback.core.boolex.EvaluationException;
import com.effigoproject.schedulers.service.NotificationScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class CronScheduler {


    @Autowired
    private NotificationScheduler notificationScheduler;

    @Scheduled(cron = "*/20 * * * * *", zone = "Asia/Kolkata")
    public void loggingScheduler(){
        notificationScheduler.logging();
    }
}
