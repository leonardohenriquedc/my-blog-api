package com.leo.my_blog_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leo.my_blog_api.dtos.PostsDocsDTO;
import com.leo.my_blog_api.service.UserAdminService;

@RestController
@RequestMapping(value = "/root")
public class UserAdminController {

  @Autowired
  UserAdminService userAdminService;

  @PostMapping(value = "/newblog")
  public ResponseEntity<PostsDocsDTO> newBlog(@RequestBody PostsDocsDTO postsDocsDTO) {
    PostsDocsDTO result = this.userAdminService.newBlog(postsDocsDTO);
    return ResponseEntity.ok(result);
  }

}
