package com.effigoproject.transactionconsumer.controller;

import com.effigoproject.transactionconsumer.service.KafkaConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);

    private final KafkaConsumerService kafkaConsumerService;

    public KafkaController(KafkaConsumerService kafkaConsumerService) {
        this.kafkaConsumerService = kafkaConsumerService;
    }

    @PostMapping("/consume-and-store")
    public String consumeAndStore() {
        logger.info("Triggering Kafka message consumption and storage...");
        kafkaConsumerService.consumeAndStoreMessages();
        return "Kafka messages consumed and stored in the database.";
    }
}