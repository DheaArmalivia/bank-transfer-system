package com.bank.transfersystem.banktransfersystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.transfersystem.banktransfersystem.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAll();

    Optional<Account> findFirstByAccountNoAndUserUserIdOrderByAccountIdAsc(String accountNo, Long userId);

    Optional<Account> findByAccountNo(String accountNo);

}
