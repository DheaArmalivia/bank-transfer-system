package com.bank.transfersystem.banktransfersystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bank.transfersystem.banktransfersystem.entity.Transaction;

@Service
public interface TransactionService {

    Transaction save(Transaction transaction);
    
    Optional<Transaction> findById(Long id);

    List<Transaction> findAll();

    void deleteById(Long id);

}
