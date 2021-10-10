package com.kstreams.events;


public class BlogCreatedEvent {

  private Long id;

  private String userId;

  private String title;

  private String content;

  public BlogCreatedEvent() {}

  public BlogCreatedEvent(Long id, String userId, String title, String content) {
    this.id = id;
    this.userId = userId;
    this.title = title;
    this.content = content;
  }

  public String getUserId() {
    return userId;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public Long getId() {return id;}
}
