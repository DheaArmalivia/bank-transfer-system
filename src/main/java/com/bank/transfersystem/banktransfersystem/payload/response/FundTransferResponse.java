package com.bank.transfersystem.banktransfersystem.payload.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FundTransferResponse {
    
    private String status;
    private Date transactionDate;
    private String sourceAccount;
    private String destinedAccount;
    private double amount;
    private String transactionID;

    public FundTransferResponse(String status, Date transactionDate) {
        this.status = status;
        this.transactionDate = transactionDate;
    }

}
