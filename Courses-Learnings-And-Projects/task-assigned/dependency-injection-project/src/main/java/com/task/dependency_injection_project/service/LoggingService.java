package com.task.dependency_injection_project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {

    private static final Logger logger = LoggerFactory.getLogger(LoggingService.class);

    public LoggingService() {
        logger.info("LoggingService: Default constructor called");
    }

    public void log(String message) {
        logger.info("LOG: {}", message);
    }
}