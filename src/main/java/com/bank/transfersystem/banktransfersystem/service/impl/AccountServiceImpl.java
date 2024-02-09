package com.bank.transfersystem.banktransfersystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.transfersystem.banktransfersystem.entity.Account;
import com.bank.transfersystem.banktransfersystem.repository.AccountRepository;
import com.bank.transfersystem.banktransfersystem.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository repository;

    @Override
    public Account save(Account account) {
        // TODO Auto-generated method stub
        return repository.save(account);
    }

    @Override
    public Optional<Account> findById(Long id) {
        // TODO Auto-generated method stub
        return repository.findById(id);
    }

    @Override
    public List<Account> findAll() {
        // TODO Auto-generated method stub
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        repository.deleteById(id);
    }

}
