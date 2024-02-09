package com.bank.transfersystem.banktransfersystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.transfersystem.banktransfersystem.entity.Transaction;
import com.bank.transfersystem.banktransfersystem.repository.TransactionRepository;
import com.bank.transfersystem.banktransfersystem.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
    
    @Autowired
    TransactionRepository repository;

    @Override
    public Transaction save(Transaction transaction) {
        // TODO Auto-generated method stub
        return repository.save(transaction);
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        // TODO Auto-generated method stub
        return repository.findById(id);
    }

    @Override
    public List<Transaction> findAll() {
        // TODO Auto-generated method stub
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        repository.deleteById(id);
    }

    

}
