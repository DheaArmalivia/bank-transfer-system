package com.bank.transfersystem.banktransfersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.transfersystem.banktransfersystem.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    
}
