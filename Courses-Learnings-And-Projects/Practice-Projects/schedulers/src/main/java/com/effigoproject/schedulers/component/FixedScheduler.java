package com.effigoproject.schedulers.component;

import com.effigoproject.schedulers.service.NotificationScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalTime;

public class FixedScheduler {

    @Autowired
    private NotificationScheduler notificationScheduler;

    @Scheduled(fixedRate = 5000) //sending notification after every 5 minutes
    public void setReminder()
    {
        notificationScheduler.setReminder();
    }

}
