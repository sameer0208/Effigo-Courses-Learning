package com.effigoproject.schedulers.component;

import com.effigoproject.schedulers.service.NotificationScheduler;
import org.springframework.scheduling.annotation.Scheduled;


public class FixedScheduler {

    private NotificationScheduler notificationScheduler;

    public FixedScheduler(NotificationScheduler notificationScheduler)
    {
        this.notificationScheduler = notificationScheduler;
    }

    @Scheduled(fixedRate = 5000)
    public void setReminder()
    {
        notificationScheduler.setReminder();
    }

}
