package com.leo.my_blog_api.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String role;

  @OneToMany(mappedBy = "roleId")
  private List<User> users = new ArrayList<>();

  public Role(Integer id, String role) {
    this.id = id;
    this.role = role;
  }

  public Role() {
  }

  @Override
  public String getAuthority() {
    return this.role;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getRole() {
    return this.role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
