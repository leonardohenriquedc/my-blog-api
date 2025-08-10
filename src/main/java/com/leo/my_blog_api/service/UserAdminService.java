package com.leo.my_blog_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.leo.my_blog_api.dtos.PostsDocsDTO;
import com.leo.my_blog_api.model.PostsDocs;
import com.leo.my_blog_api.model.User;
import com.leo.my_blog_api.repository.PostsDocsRepository;
import com.leo.my_blog_api.repository.UserRepository;
import com.leo.my_blog_api.dtos.UserDTO;

@Service
public class UserAdminService implements UserDetailsService {

  @Autowired
  PostsDocsRepository postsDocsRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleService roleService;

  public PostsDocsDTO newBlog(PostsDocsDTO postsDocsDTO) {

    PostsDocs postsDocs = new PostsDocs(null, postsDocsDTO.getTitle(), postsDocsDTO.getBody(),
        postsDocsDTO.getStyleBody());

    PostsDocs result = this.postsDocsRepository.save(postsDocs);

    return new PostsDocsDTO(result.getId(), result.getTitle(), result.getBody(), result.getStyleBody());

  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> result = this.userRepository.findByEmail(username);

    if (result.isEmpty()) {
      throw new RuntimeException("Usuario não existe");
    }

    System.out.println(result.get().getRoleId());

    return result.get();
  }

  public UserDTO newUser(UserDTO userDTO) {

    try {
      User user = new User(null, userDTO.getName(), userDTO.getEmail(), null,
          this.roleService.getRoleByRoleName("ROLE_USER"));

      String passwordEncoded = new BCryptPasswordEncoder().encode(userDTO.getPassword());

      user.setPassword(passwordEncoded);

      user = this.userRepository.save(user);

      return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword());

    } catch (Exception e) {
      throw new RuntimeException("could not create user");
    }
    /*
     * User user = new User(null, userDTO.getName(), userDTO.getEmail(), null,
     * this.roleService.getRoleByRoleName("ROLE_USER"));
     * 
     * String passwordEncoded = new
     * BCryptPasswordEncoder().encode(userDTO.getPassword());
     * 
     * user.setPassword(passwordEncoded);
     * 
     * user = this.userRepository.save(user);
     * 
     * return new UserDTO(user.getId(), user.getName(), user.getEmail(),
     * user.getPassword());
     */
  }
}
