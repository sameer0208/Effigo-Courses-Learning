package com.effigoproject.guava_caching.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic Account Information
    private String accountNumber;
    private String accountHolderName;
    private String ifscCode;
    private String branchName;
    private Double balance;
    private String accountType; // Savings, Current, etc.
    private LocalDate accountOpeningDate;
    private boolean isActive;

    // Customer Personal Details
    private String customerId;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationality;

    // KYC Information
    private String aadharNumber;
    private String panCardNumber;
    private String passportNumber;
    private String voterId;
    private boolean kycCompleted;

    // Nominee Details
    private String nomineeName;
    private String nomineeRelation;
    private String nomineeContact;
    private String nomineeAddress;

    // Employment & Income Details
    private String occupation;
    private String employerName;
    private Double annualIncome;
    private String incomeSource;

    // Loan & Credit Information
    private boolean hasLoan;
    private Double loanAmount;
    private Double emiAmount;
    private Double creditScore;

    // Card Details
    private String debitCardNumber;
    private String creditCardNumber;
    private LocalDate debitCardExpiry;
    private LocalDate creditCardExpiry;
    private boolean isCardActive;

    // Security Information
    private String passwordHash;
    private int failedLoginAttempts;
    private boolean accountLocked;

    // Online Banking Preferences
    private boolean internetBankingEnabled;
    private boolean mobileBankingEnabled;
    private boolean smsAlertsEnabled;
    private boolean emailAlertsEnabled;

    // Transaction Limits
    private Double dailyWithdrawalLimit;
    private Double monthlyTransferLimit;

    // Additional Information
    private LocalDate lastUpdated;
    private String notes;
}
