package com.leo.my_blog_api.service;

import java.time.Instant;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

  @Value("secretKey")
  String secretKey;

  public String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(Date.from(getTime()))
        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), io.jsonwebtoken.SignatureAlgorithm.HS256)
        .compact();

  }

  public String extractUsername(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(secretKey.getBytes())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();

  }

  public boolean isValid(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public Instant getTime() {
    return Instant.now().plusSeconds(60 * 60);
  }
}
