package com.leo.my_blog_api.dtos;

public class TitleAndId {
  private Integer id;

  private String title;

  public TitleAndId() {
  }

  public TitleAndId(Integer id, String title) {
    this.id = id;
    this.title = title;
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
}
