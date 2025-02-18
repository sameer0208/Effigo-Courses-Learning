package com.task.dependency_injection_project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentProcessor {

    private static final Logger logger = LoggerFactory.getLogger(PaymentProcessor.class);

    private final PaymentService paymentService;
    private LoggingService loggingService;

    @Autowired
    public PaymentProcessor(PaymentService paymentService) {
        this.paymentService = paymentService;
        logger.info("PaymentProcessor: Constructor Injection used for PaymentService");
    }

    @Autowired
    public void setLoggingService(LoggingService loggingService) {
        this.loggingService = loggingService;
        logger.info("PaymentProcessor: Setter Injection used for LoggingService");
    }

    public void processPayment() {
        paymentService.processPayment();
        if (loggingService != null) {
            loggingService.log("Payment processed successfully!");
        }
    }
}