package com.leo.my_blog_api.service;

import java.io.IOException;

import org.checkerframework.checker.units.qual.t;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private UserAdminService userAdminService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    try {

      String token = request.getHeader("Authorization");

      if (token != null && token.startsWith("Bearer ")) {

        token = token.substring(7);

        System.out.println("Token esta sem o Bearer: " + token);

        String subject = jwtUtils.isValid(token) ? jwtUtils.extractUsername(token) : "";

        if (subject.isBlank())
          throw new RuntimeException("subject e nulo");

        UserDetails userDetails = userAdminService.loadUserByUsername(subject);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(subject, null,
            userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }

      filterChain.doFilter(request, response);

    } catch (UsernameNotFoundException ex) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      response.getWriter().write("Usuário não encontrado");
      response.getWriter().flush();
    }
  }
}
