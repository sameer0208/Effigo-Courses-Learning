package com.task.dependency_injection_project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreditCardPaymentService implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(CreditCardPaymentService.class);

    public CreditCardPaymentService() {
        logger.info("CreditCardPaymentService: Constructor Injection used");
    }

    @Override
    public void processPayment() {
        logger.info("Processing Credit Card Payment...");
    }
}
