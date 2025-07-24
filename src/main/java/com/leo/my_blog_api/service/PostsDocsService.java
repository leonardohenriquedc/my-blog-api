package com.leo.my_blog_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leo.my_blog_api.dtos.PostsDocsDTO;
import com.leo.my_blog_api.dtos.TitleAndId;
import com.leo.my_blog_api.model.PostsDocs;
import com.leo.my_blog_api.repository.PostsDocsRepository;

@Service
public class PostsDocsService {

  @Autowired
  PostsDocsRepository postsDocsRepository;

  public PostsDocsDTO getPostDoc(Integer id) {
    PostsDocs post = this.postsDocsRepository.getReferenceById(String.valueOf(id));

    return new PostsDocsDTO(post.getId(), post.getTitle(), post.getBody(), post.getStyleBody());
  }

  public List<TitleAndId> getAllTitles() {
    Optional<List<TitleAndId>> result = postsDocsRepository.getAllTitles();

    if (result.isEmpty()) {
      throw new RuntimeException("Titles not found");
    }

    return result.get();
  }
}
