package com.bank.transfersystem.banktransfersystem.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JWTResponse {

    private Long userId;
    private String name;
    private String token;
    
}
