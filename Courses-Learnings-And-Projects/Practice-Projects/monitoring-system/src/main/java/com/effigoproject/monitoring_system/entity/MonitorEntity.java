package com.effigoproject.monitoring_system.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "monitoring_data")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MonitorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("metricName")
    @Column(name = "metric_name")
    private String metricName;

    @JsonProperty("metricValue")
    @Column(name = "metric_value")
    private double metricValue;
}