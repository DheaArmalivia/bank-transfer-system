package com.bank.transfersystem.banktransfersystem.payload.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FundTransferRequest {
    
    private String sourceAccount;
    private String destinedAccount;
    private double amount;
    private String notes;

}
