package com.task.dependency_injection_project.controller;

import com.task.dependency_injection_project.service.PaymentProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PaymentController {

    private final PaymentProcessor paymentProcessor;

    public PaymentController(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    @GetMapping("/pay")
    public String makePayment() {
        paymentProcessor.processPayment();
        return "Payment Processed Successfully!";
    }
}