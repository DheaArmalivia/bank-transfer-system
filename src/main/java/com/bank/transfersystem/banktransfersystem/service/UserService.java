package com.bank.transfersystem.banktransfersystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bank.transfersystem.banktransfersystem.entity.User;

@Service
public interface UserService {

    User save(User user);

    Optional<User> findById(Long id);

    void deleteById(Long id);

    List<User> findAll();

}
