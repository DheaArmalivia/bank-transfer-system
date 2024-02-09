package com.bank.transfersystem.banktransfersystem.security.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.bank.transfersystem.banktransfersystem.security.service.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JWTUtils {

    @Value("${btsystem.jwtSecret}")
    private String jwtSecret;

    @Value("${btsystem.expirationTime}")
    private int jwtExpirationTime;

    public String generateJWTToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
            .setSubject((userPrincipal.getUsername()))
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationTime))
            .signWith(key(), SignatureAlgorithm.HS256)
            .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
      }
    
      public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                   .parseClaimsJws(token).getBody().getSubject();
      }
    
      public boolean validateJWTToken(String authToken) {
        try {
          Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
          return true;
        } catch (MalformedJwtException e) {
          log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
          log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
          log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
          log.error("JWT claims string is empty: {}", e.getMessage());
        }
    
        return false;
      }
}
