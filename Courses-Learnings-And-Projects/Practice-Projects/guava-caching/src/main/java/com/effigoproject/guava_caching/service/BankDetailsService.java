package com.effigoproject.guava_caching.service;

import com.effigoproject.guava_caching.entity.BankDetails;
import com.effigoproject.guava_caching.repository.BankDetailsRepository;
import com.google.common.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class BankDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(BankDetailsService.class);

    private final BankDetailsRepository bankDetailsRepository;
    private final Cache<String, BankDetails> bankDetailsCache;

    @Autowired
    public BankDetailsService(BankDetailsRepository bankDetailsRepository, Cache<String, BankDetails> bankDetailsCache) {
        this.bankDetailsRepository = bankDetailsRepository;
        this.bankDetailsCache = bankDetailsCache;
    }

    // Save new BankDetails to DB and cache
    public BankDetails saveBankDetails(BankDetails bankDetails) {
        BankDetails savedDetails = bankDetailsRepository.save(bankDetails);
        bankDetailsCache.put(savedDetails.getAccountNumber(), savedDetails);
        logger.info("[CACHE] Saved bank details for account: {} in cache", savedDetails.getAccountNumber());
        return savedDetails;
    }

    // Fetch BankDetails by Account Number (from cache or DB)
    public BankDetails getBankDetailsByAccountNumber(String accountNumber) {
        BankDetails cachedDetails = bankDetailsCache.getIfPresent(accountNumber);
        if (cachedDetails != null) {
            logger.info("[CACHE HIT] Retrieved bank details for account: {} from cache", accountNumber);
            return cachedDetails;
        } else {
            logger.info("[CACHE MISS] Bank details for account: {} not found in cache. Fetching from DB...", accountNumber);
            return getBankDetailsFromDb(accountNumber);
        }
    }

    // Fetch from DB if not in cache and update cache
    public BankDetails getBankDetailsFromDb(String accountNumber) {
        try {
            return bankDetailsCache.get(accountNumber, () -> {
                Optional<BankDetails> bankDetails = bankDetailsRepository.findByAccountNumber(accountNumber);
                if (bankDetails.isPresent()) {
                    logger.info("[CACHE] Fetched bank details for account: {} from DB and updated cache", accountNumber);
                    return bankDetails.get();
                } else {
                    throw new RuntimeException("Bank details not found for account: " + accountNumber);
                }
            });
        } catch (ExecutionException e) {
            logger.error("[CACHE ERROR] Error fetching bank details for account: {}", accountNumber, e);
            throw new RuntimeException("Error fetching bank details", e);
        }
    }

    // Update Bank Details (in DB and cache)
    public BankDetails updateBankDetails(String accountNumber, BankDetails updatedDetails) {
        Optional<BankDetails> existingDetails = bankDetailsRepository.findByAccountNumber(accountNumber);
        if (existingDetails.isPresent()) {
            BankDetails bankDetails = existingDetails.get();
            bankDetails.setBalance(updatedDetails.getBalance());
            bankDetails.setBranchName(updatedDetails.getBranchName());
            bankDetails.setAccountHolderName(updatedDetails.getAccountHolderName());
            BankDetails savedDetails = bankDetailsRepository.save(bankDetails);
            bankDetailsCache.put(accountNumber, savedDetails);
            logger.info("[CACHE] Updated bank details for account: {} in DB and cache", accountNumber);
            return savedDetails;
        } else {
            throw new RuntimeException("Account not found: " + accountNumber);
        }
    }

    // Delete Bank Details (from DB and cache)
    public void deleteBankDetails(String accountNumber) {
        bankDetailsRepository.findByAccountNumber(accountNumber).ifPresent(bankDetails -> {
            bankDetailsRepository.delete(bankDetails);
            bankDetailsCache.invalidate(accountNumber); // Remove from cache
            logger.info("[CACHE] Deleted bank details for account: {} from DB and cache", accountNumber);
        });
    }

    // Get All Bank Details (From DB - no cache for bulk operations)
    public List<BankDetails> getAllBankDetails() {
        logger.info("[DB] Fetching all bank details from DB");
        return bankDetailsRepository.findAll();
    }

    // Clear Cache (Utility method)
    public void clearCache() {
        bankDetailsCache.invalidateAll();
        logger.info("[CACHE] Cleared all entries from cache");
    }
}