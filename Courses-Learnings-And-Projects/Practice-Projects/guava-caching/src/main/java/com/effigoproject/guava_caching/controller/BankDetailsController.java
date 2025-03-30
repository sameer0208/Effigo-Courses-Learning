package com.effigoproject.guava_caching.controller;

import com.effigoproject.guava_caching.entity.BankDetails;
import com.effigoproject.guava_caching.service.BankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank-details")
public class BankDetailsController {

    private final BankDetailsService bankDetailsService;

    @Autowired
    public BankDetailsController(BankDetailsService bankDetailsService) {
        this.bankDetailsService = bankDetailsService;
    }

    // ✅ Fetch Bank Details by Account Number (Cache or DB)
    @GetMapping("/{accountNumber}")
    public ResponseEntity<BankDetails> getBankDetailsByAccountNumber(@PathVariable String accountNumber) {
        BankDetails bankDetails = bankDetailsService.getBankDetailsFromDb(accountNumber);
        if (bankDetails == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bankDetails);
    }

    // ✅ Add Bank Details (Saves to DB and Cache)
    @PostMapping
    public ResponseEntity<BankDetails> addBankDetails(@RequestBody BankDetails bankDetails) {
        BankDetails savedDetails = bankDetailsService.saveBankDetails(bankDetails);
        return ResponseEntity.ok(savedDetails);
    }

    // ✅ Update Bank Details by Account Number (Updates DB and Cache)
    @PutMapping("/{accountNumber}")
    public ResponseEntity<BankDetails> updateBankDetails(@PathVariable String accountNumber,
                                                         @RequestBody BankDetails updatedDetails) {
        try {
            BankDetails updated = bankDetailsService.updateBankDetails(accountNumber, updatedDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Delete Bank Details by Account Number (Removes from DB and Cache)
    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<String> deleteBankDetails(@PathVariable String accountNumber) {
        bankDetailsService.deleteBankDetails(accountNumber);
        return ResponseEntity.ok("Bank details deleted successfully!");
    }

    // ✅ Fetch All Bank Details (From DB - No Cache for Bulk)
    @GetMapping
    public ResponseEntity<List<BankDetails>> getAllBankDetails() {
        List<BankDetails> allDetails = bankDetailsService.getAllBankDetails();
        return ResponseEntity.ok(allDetails);
    }

    // ✅ Clear Cache (Utility Endpoint)
    @DeleteMapping("/cache")
    public ResponseEntity<String> clearCache() {
        bankDetailsService.clearCache();
        return ResponseEntity.ok("Bank details cache cleared!");
    }
}
