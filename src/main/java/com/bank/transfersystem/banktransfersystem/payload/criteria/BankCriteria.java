package com.bank.transfersystem.banktransfersystem.payload.criteria;

import lombok.*;

@Getter
@Setter
public class BankCriteria {

    private Long bankId;

    private String bankName;

    private String bankAddress;

    private AccountCriteria account;

    
}
