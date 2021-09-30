package com.kstreams.events;


public class BlogCreatedEvent {

  private String userId;

  private String title;

  private String content;

  public BlogCreatedEvent() {}

  public BlogCreatedEvent(String userId, String title, String content) {
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
}
