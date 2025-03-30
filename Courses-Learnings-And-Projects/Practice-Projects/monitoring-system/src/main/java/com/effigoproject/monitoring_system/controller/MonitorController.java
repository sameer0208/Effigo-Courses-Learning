package com.effigoproject.monitoring_system.controller;

import com.effigoproject.monitoring_system.entity.MonitorEntity;
import com.effigoproject.monitoring_system.service.MonitorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metrics")
public class MonitorController {

    private final MonitorService monitorService;

    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @PostMapping("/add")
    public MonitorEntity addMetric(@RequestBody MonitorEntity metric) {
        return monitorService.addMetric(metric);
    }

    @GetMapping("/all")
    public List<MonitorEntity> getAllMetrics() {
        return monitorService.getAllMetrics();
    }
}