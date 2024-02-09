package com.bank.transfersystem.banktransfersystem.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.transfersystem.banktransfersystem.entity.User;
import com.bank.transfersystem.banktransfersystem.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("User with name: " + username + " not found"));

        return UserDetailsImpl.build(user);
    }
    
}
