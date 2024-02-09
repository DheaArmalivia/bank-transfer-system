package com.bank.transfersystem.banktransfersystem.payload.criteria;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCriteria {
    
    private Long transactionId;

    private Date transactionDate;

    private double amount;

    private AccountCriteria sourceAccount;

    private AccountCriteria destinedAccount;

}
