package com.leo.my_blog_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import com.leo.my_blog_api.dtos.TitleAndId;
import com.leo.my_blog_api.model.PostsDocs;

@Repository
public interface PostsDocsRepository extends JpaRepository<PostsDocs, String> {

  @Query(nativeQuery = true, value = "SELECT id, title FROM posts_docs")
  Optional<List<TitleAndId>> getAllTitles();
}
