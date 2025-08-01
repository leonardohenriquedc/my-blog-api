package com.leo.my_blog_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leo.my_blog_api.repository.RoleRepository;
import com.leo.my_blog_api.model.Role;

@Service
public class RoleService {

  @Autowired
  RoleRepository roleRepository;

  public Role getRoleByRoleName(String roleName) {
    Optional<Role> result = this.roleRepository.findByRole(roleName);

    if (result.isEmpty()) {
      throw new RuntimeException("Role não existe");
    }

    return result.get();
  }
}
