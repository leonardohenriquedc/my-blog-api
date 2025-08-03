package com.leo.my_blog_api.service;

import java.time.Instant;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

  @Value("${secretKey}")
  private String secretKey;

  public String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(Date.from(getTime()))
        .signWith(Keys.hmacShaKeyFor(getSecretKeyDecoded(this.secretKey)), io.jsonwebtoken.SignatureAlgorithm.HS256)
        .compact();

  }

  public String extractUsername(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSecretKeyDecoded(token))
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();

  }

  public boolean isValid(String token) {
    System.out.println("Este e o token do isValid: " + token);
    try {
      Jwts.parserBuilder().setSigningKey(getSecretKeyDecoded(this.secretKey)).build().parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      System.out.println("retornou false");
      return false;
    }
  }

  private Instant getTime() {
    return Instant.now().plusSeconds(60 * 60);
  }

  private byte[] getSecretKeyDecoded(String secrekey) {
    return Decoders.BASE64.decode(secretKey);
  }
}
