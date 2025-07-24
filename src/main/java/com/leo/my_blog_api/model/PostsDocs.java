package com.leo.my_blog_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts_docs")
public class PostsDocs {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String title;
  private String body;
  private String styleBody;

  public PostsDocs(Integer id, String title, String body, String styleBody) {
    this.id = id;
    this.title = title;
    this.body = body;
    this.styleBody = styleBody;
  }

  public PostsDocs() {
  }

  @Override
  public String toString() {
    return new String(
        "Id: " + this.id + "\nTitle: " + this.title + "\nBody: " + this.body + "\nStyle: " + this.styleBody);
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return this.body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getStyleBody() {
    return this.styleBody;
  }

  public void setStyleBody(String styleBody) {
    this.styleBody = styleBody;
  }
}
