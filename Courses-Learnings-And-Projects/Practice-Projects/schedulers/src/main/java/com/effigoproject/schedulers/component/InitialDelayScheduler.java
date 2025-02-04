package com.effigoproject.schedulers.component;

import com.effigoproject.schedulers.service.NotificationScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalTime;

public class InitialDelayScheduler {

    @Autowired
    private NotificationScheduler notificationScheduler;

    @Scheduled(fixedRate = 5000, initialDelay = 10000) // 10 Second delay before first run
    public void delayedTask()
    {
        notificationScheduler.delayedTask();
    }


}
