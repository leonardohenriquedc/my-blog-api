package com.leo.my_blog_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leo.my_blog_api.dtos.PostsDocsDTO;
import com.leo.my_blog_api.model.PostsDocs;
import com.leo.my_blog_api.repository.PostsDocsRepository;

@Service
public class UserAdminService {

  @Autowired
  PostsDocsRepository postsDocsRepository;

  public PostsDocsDTO newBlog(PostsDocsDTO postsDocsDTO) {

    PostsDocs postsDocs = new PostsDocs(null, postsDocsDTO.getTitle(), postsDocsDTO.getBody(),
        postsDocsDTO.getStyleBody());

    PostsDocs result = this.postsDocsRepository.save(postsDocs);

    return new PostsDocsDTO(result.getId(), result.getTitle(), result.getBody(), result.getStyleBody());

  }
}
