package com.bank.transfersystem.banktransfersystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.bank.transfersystem.banktransfersystem.entity.User;
import com.bank.transfersystem.banktransfersystem.payload.request.LoginRequest;
import com.bank.transfersystem.banktransfersystem.payload.request.SignupRequest;
import com.bank.transfersystem.banktransfersystem.payload.response.GeneralResponse;
import com.bank.transfersystem.banktransfersystem.payload.response.JWTResponse;
import com.bank.transfersystem.banktransfersystem.security.jwt.JWTUtils;
import com.bank.transfersystem.banktransfersystem.security.service.UserDetailsImpl;
import com.bank.transfersystem.banktransfersystem.service.UserService;

import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/rest/api")
@Log4j2
public class LoginController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JWTUtils jwtUtils;

    GeneralResponse genResp = new GeneralResponse();

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody LoginRequest request) {
        //TODO: process POST request
        Authentication authentication = authManager
        .authenticate(new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJWTToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new ResponseEntity<>(
            new JWTResponse(userDetails.getUserId(), userDetails.getUsername(), jwt), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<GeneralResponse> registerUser(@RequestBody SignupRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setPassword(encoder.encode(request.getPassword()));

        userService.save(user);

        genResp = new GeneralResponse("User registered successfully!", false, user);

        return new ResponseEntity<>(genResp, HttpStatus.OK);
    }

    @GetMapping("/testlogger")
    public void testLogger() {
        log.trace("logger trace");
        log.debug("logger debug");
        log.info("logger info");
        log.warn("logger warn");
        log.error("logger error");
        
    }
    
    
    
}
