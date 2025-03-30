package com.effigoproject.transactionproducer.service;

import com.effigoproject.transactionproducer.entity.MasterItem;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class MasterItemProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterItemProducer.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ExecutorService fallbackExecutor = Executors.newFixedThreadPool(10);
    @Value("${app.kafka.topic.master-item}")
    private String masterItemTopic;
    @Value("${app.kafka.topic.fallback}")
    private String fallbackTopic;

    public void sendMasterItem(MasterItem masterItem) {
        LOGGER.info("Sending message to master-item-topic: {}", masterItem);

        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(masterItemTopic, masterItem);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                LOGGER.info("Message successfully sent to master-item-topic: {}", masterItem);
            } else {
                LOGGER.error("Error while sending message to master-item-topic. Sending to fallback-topic: {}", masterItem, ex);
                fallbackExecutor.submit(() -> {
                    try {
                        kafkaTemplate.send(fallbackTopic, masterItem);
                        LOGGER.info("Message sent to fallback-topic: {}", masterItem);
                    } catch (Exception e) {
                        LOGGER.error("Failed to send message to fallback-topic: {}", masterItem, e);
                    }
                });
            }
        });
    }
}