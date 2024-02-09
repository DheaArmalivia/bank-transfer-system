package com.bank.transfersystem.banktransfersystem.payload.criteria;

import java.math.BigDecimal;

import com.bank.transfersystem.banktransfersystem.entity.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountCriteria {
    
    private Long accountId;

    private BigDecimal balance;

    private String accountNo;

    private User user;

    private Bank bank;

    private Transaction sourceAccount;

    private Transaction destinedAccount;

}
