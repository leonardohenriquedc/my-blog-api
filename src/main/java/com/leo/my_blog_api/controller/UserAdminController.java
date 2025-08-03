package com.leo.my_blog_api.controller;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leo.my_blog_api.dtos.AuthRequest;
import com.leo.my_blog_api.dtos.PostsDocsDTO;
import com.leo.my_blog_api.dtos.UserDTO;
import com.leo.my_blog_api.service.JwtUtils;
import com.leo.my_blog_api.service.UserAdminService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*") // ou o domínio do seu frontend
@RestController
@RequestMapping(value = "/root")
public class UserAdminController {

  @Autowired
  UserAdminService userAdminService;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  AuthenticationManager authManager;

  @GetMapping
  public ResponseEntity<String> teste() {
    return ResponseEntity.ok("Teste");
  }

  @PostMapping(value = "/newblog")
  public ResponseEntity<PostsDocsDTO> newBlog(@RequestBody PostsDocsDTO postsDocsDTO) {
    System.out.println("O metodo newblog foi chamado");
    PostsDocsDTO result = this.userAdminService.newBlog(postsDocsDTO);
    return ResponseEntity.ok(result);
  }

  @PostMapping(value = "/login")
  public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
    System.out.println("O metodo login - controller foi chamado");
    Authentication authentication = authManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

    String token = jwtUtils.generateToken(request.getEmail());
    return ResponseEntity.ok(token);
  }

  @PostMapping(value = "/create")
  public ResponseEntity<UserDTO> newUser(@RequestBody @Valid UserDTO userDTO) {
    System.out.println("O methodo foi chamado 'newUser - controller'");
    userDTO = this.userAdminService.newUser(userDTO);

    return ResponseEntity.ok(userDTO);
  }
}
