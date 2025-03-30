package com.effigoproject.schedulers.component;

import com.effigoproject.schedulers.service.NotificationScheduler;
import org.springframework.scheduling.annotation.Scheduled;


public class InitialDelayScheduler {

    private NotificationScheduler notificationScheduler;

    public InitialDelayScheduler(NotificationScheduler notificationScheduler)
    {
        this.notificationScheduler = notificationScheduler;
    }

    @Scheduled(fixedRate = 5000, initialDelay = 10000)
    public void delayedTask()
    {
        notificationScheduler.delayedTask();
    }


}
