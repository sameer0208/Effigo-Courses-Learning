package com.effigoproject.monitoring_system.service;

import com.effigoproject.monitoring_system.entity.MonitorEntity;
import com.effigoproject.monitoring_system.repository.MonitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorService {

    private final MonitorRepository monitorRepository;

    public MonitorService(MonitorRepository monitorRepository) {
        this.monitorRepository = monitorRepository;
    }

    public MonitorEntity addMetric(MonitorEntity metric) {
        return monitorRepository.save(metric);
    }

    public List<MonitorEntity> getAllMetrics() {
        return monitorRepository.findAll();
    }
}
