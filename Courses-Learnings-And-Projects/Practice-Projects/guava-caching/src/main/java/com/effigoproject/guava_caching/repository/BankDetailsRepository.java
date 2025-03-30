package com.effigoproject.guava_caching.repository;


import com.effigoproject.guava_caching.entity.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails, Long> {
    Optional<BankDetails> findByAccountNumber(String accountNumber);
}