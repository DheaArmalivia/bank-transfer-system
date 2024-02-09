package com.bank.transfersystem.banktransfersystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.transfersystem.banktransfersystem.entity.User;
import com.bank.transfersystem.banktransfersystem.repository.UserRepository;
import com.bank.transfersystem.banktransfersystem.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public User save(User user) {
        // TODO Auto-generated method stub
        return repository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        // TODO Auto-generated method stub
        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        repository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return repository.findAll();
    }


}