package com.effigoproject.transactionconsumer.service;

import com.effigoproject.transactionconsumer.entity.KafkaItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducerService.class);
    private final KafkaTemplate<String, KafkaItem> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, KafkaItem> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendKafkaItem(KafkaItem kafkaItem) {
        log.info("Sending Kafka Item to transaction-topic: {}", kafkaItem);
        kafkaTemplate.send("transaction-topic", kafkaItem);
    }
}
