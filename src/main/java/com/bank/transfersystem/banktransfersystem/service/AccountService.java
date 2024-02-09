package com.bank.transfersystem.banktransfersystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bank.transfersystem.banktransfersystem.entity.Account;

@Service
public interface AccountService {

    Account save(Account transaction);
    
    Optional<Account> findById(Long id);

    List<Account> findAll();

    void deleteById(Long id);

}
