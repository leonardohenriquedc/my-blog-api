package com.leo.my_blog_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.leo.my_blog_api.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

  Optional<Role> findByRole(String role);
}
