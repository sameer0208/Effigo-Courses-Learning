package com.effigoproject.monitoring_system_jmx_exporter.repository;

import com.effigoproject.monitoring_system_jmx_exporter.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}