package com.effigoproject.schedulers.controller;


import com.effigoproject.schedulers.service.DynamicScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping("/scheduler")
public class SchedulerController {
    @Autowired
    private DynamicScheduler dynamicScheduler;

    @PostMapping("/start")
    public String startScheduler()
    {
        dynamicScheduler.enableScheduler();
        return "Scheduler started successfully at "+ LocalTime.now();
    }

    @PostMapping("/stop")
    public String stopScheduler()
    {
        dynamicScheduler.disableScheduler();
        return "Scheduler stopped successfully at "+LocalTime.now();
    }


}
