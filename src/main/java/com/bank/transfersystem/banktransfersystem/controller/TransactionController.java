package com.bank.transfersystem.banktransfersystem.controller;

import org.springframework.web.bind.annotation.RestController;

import com.bank.transfersystem.banktransfersystem.entity.Account;
import com.bank.transfersystem.banktransfersystem.entity.Transaction;
import com.bank.transfersystem.banktransfersystem.payload.request.FundTransferRequest;
import com.bank.transfersystem.banktransfersystem.payload.response.FundTransferResponse;
import com.bank.transfersystem.banktransfersystem.service.AccountService;
import com.bank.transfersystem.banktransfersystem.service.TransactionService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/rest/api/transaction")
public class TransactionController {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @PostMapping("/fund-transfer")
    public ResponseEntity<?> fundTransfer(@RequestBody FundTransferRequest request) {
        //TODO: process POST request

        Optional<Account> fromAccount = accountService.findByAccountNo(request.getSourceAccount());
        Optional<Account> toAccount = accountService.findByAccountNo(request.getDestinedAccount());

        if(!fromAccount.isPresent()) {
            return new ResponseEntity<>(new FundTransferResponse("Account is not exist", new Date()), HttpStatus.BAD_REQUEST);
        }

        if(!toAccount.isPresent()) {
            return new ResponseEntity<>(new FundTransferResponse("Account is not exist", new Date()), HttpStatus.BAD_REQUEST);
        }

        if (fromAccount.get().getBalance() < request.getAmount()) {
            return new ResponseEntity<>(new FundTransferResponse("Transaction failed, insufficient balance", new Date()), HttpStatus.BAD_REQUEST);
        }

        Account sourceAccount = fromAccount.get();
        Account destinedAccount = toAccount.get();

        Transaction trx = Transaction.builder()
            .sourceAccount(sourceAccount)
            .destinedAccount(destinedAccount)
            .amount(request.getAmount())
            .transactionDate(new Date()).build();
        trx = transactionService.save(trx);

        sourceAccount.setBalance(sourceAccount.getBalance() - request.getAmount());
        destinedAccount.setBalance(destinedAccount.getBalance() + request.getAmount());

        accountService.save(sourceAccount);
        accountService.save(destinedAccount);

        return new ResponseEntity<FundTransferResponse>(
            FundTransferResponse.builder()
                .status("Fund Transfer Successful")
                .transactionDate(new Date())
                .sourceAccount(sourceAccount.getAccountNo())
                .destinedAccount(destinedAccount.getAccountNo())
                .amount(trx.getAmount())
                .transactionID(trx.getTransactionId().toString())
                .build(), HttpStatus.OK);
    }
    
}
