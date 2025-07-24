package com.leo.my_blog_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leo.my_blog_api.dtos.PostsDocsDTO;
import com.leo.my_blog_api.dtos.TitleAndId;
import com.leo.my_blog_api.service.PostsDocsService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/post")
public class PostsDocsController {

  @Autowired
  PostsDocsService postsDocsService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<PostsDocsDTO> getPost(@PathVariable String id) {
    PostsDocsDTO postsDocs = postsDocsService.getPostDoc(Integer.parseInt(id));
    System.out.println("Foi feito uma requisão para: getPost, objeto: " + postsDocs.toString());
    return ResponseEntity.ok(postsDocs);
  }

  @GetMapping
  public ResponseEntity<List<TitleAndId>> getAllTitles() {
    List<TitleAndId> result = postsDocsService.getAllTitles();
    System.out.println("Foi feito uma requisição para: getAllTitles, objeto: " + result.toString());
    return ResponseEntity.ok(result);
  }
}
