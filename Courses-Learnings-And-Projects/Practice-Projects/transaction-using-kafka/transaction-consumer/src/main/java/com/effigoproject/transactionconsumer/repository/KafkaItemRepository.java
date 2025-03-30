package com.effigoproject.transactionconsumer.repository;

import com.effigoproject.transactionconsumer.entity.KafkaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KafkaItemRepository extends JpaRepository<KafkaItem, String> {
}
