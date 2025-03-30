package com.effigoproject.monitoring_system.repository;

import com.effigoproject.monitoring_system.entity.MonitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepository extends JpaRepository<MonitorEntity, Long> {
}