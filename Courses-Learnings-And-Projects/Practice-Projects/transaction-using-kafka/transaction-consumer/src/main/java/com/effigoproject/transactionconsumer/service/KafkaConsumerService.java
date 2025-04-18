package com.effigoproject.transactionconsumer.service;

import com.effigoproject.transactionconsumer.constants.AppConstants;
import com.effigoproject.transactionconsumer.entity.KafkaItem;
import com.effigoproject.transactionconsumer.repository.KafkaItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    private final KafkaItemRepository kafkaItemRepository;
    private final ObjectMapper objectMapper;
    private final KafkaProducerService kafkaProducerService;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public KafkaConsumerService(KafkaItemRepository kafkaItemRepository, ObjectMapper objectMapper, KafkaProducerService kafkaProducerService) {
        this.kafkaItemRepository = kafkaItemRepository;
        this.objectMapper = objectMapper;
        this.kafkaProducerService = kafkaProducerService;
    }

    @KafkaListener(topics = "master-item-topic", groupId = "transaction-consumer-group")
    @Transactional
    public void consumeMessage(String message) {
        try {
            KafkaItem kafkaItem = objectMapper.readValue(message, KafkaItem.class);
            kafkaItemRepository.save(kafkaItem);
            logger.info("Message consumed and saved: {}", kafkaItem.getItemId());
        } catch (Exception e) {
            logger.error("Failed to process Kafka message: {}", e.getMessage());
        }
    }

    public void consumeAndStoreMessages() {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", "transaction-consumer-group");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Collections.singletonList("master-item-topic"));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                if (records.isEmpty()) {
                    break;
                }

                for (ConsumerRecord<String, String> record : records) {
                    try {
                        KafkaItem kafkaItem = objectMapper.readValue(record.value(), KafkaItem.class);
                        kafkaItemRepository.save(kafkaItem);
                        logger.info("Message consumed and saved: {}", kafkaItem.getItemId());
                    } catch (Exception e) {
                        logger.error("Failed to process Kafka message: {}", e.getMessage());
                    }
                }
            }
        }
    }

    @Scheduled(fixedRate = AppConstants.SCHEDULER_FIXED_RATE)
    public void moveMessagesFromFallbackToTransactionTopic() {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", "fallback-consumer-group");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        props.put("enable.auto.commit", "false"); // Disable auto-commit

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Collections.singletonList("fallback-topic"));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                if (records.isEmpty()) {
                    break;
                }

                for (ConsumerRecord<String, String> record : records) {
                    try {
                        KafkaItem kafkaItem = objectMapper.readValue(record.value(), KafkaItem.class);

                        kafkaProducerService.sendKafkaItem(kafkaItem);

                        consumer.commitSync();

                        logger.info("Message moved from fallback-topic to transaction-topic: {}", kafkaItem.getItemId());
                    } catch (Exception e) {
                        logger.error("Failed to process Kafka message from fallback-topic: {}", e.getMessage());
                    }
                }
            }
        }
    }
}