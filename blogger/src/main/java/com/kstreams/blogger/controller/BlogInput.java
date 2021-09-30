package com.kstreams.blogger.controller;

import lombok.Data;

@Data
public class BlogInput {

  private String userId;

  private String title;

  private String content;
}
