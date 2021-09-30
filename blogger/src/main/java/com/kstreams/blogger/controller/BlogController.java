package com.kstreams.blogger.controller;

import com.kstreams.blogger.model.Blog;
import com.kstreams.blogger.service.BlogService;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("blogs")
@RequiredArgsConstructor
public class BlogController {

  private final BlogService blogService;

  @PostMapping
  public CompletableFuture<Map<String, ?>> create(@RequestBody BlogInput input) {
    var blog = Blog.builder().userId(input.getUserId()).title(input.getTitle())
        .content(input.getContent()).build();
    var blogEntityFuture = blogService.save(blog);
    return blogEntityFuture.thenApply(b -> Map.of("id", b.getId()));
  }

  @GetMapping
  public List<Blog> findAll() {
    return blogService.findAll();
  }
}
