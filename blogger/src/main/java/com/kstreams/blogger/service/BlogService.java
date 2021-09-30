package com.kstreams.blogger.service;

import com.kstreams.blogger.model.Blog;
import com.kstreams.blogger.repository.BlogRepository;
import com.kstreams.events.BlogCreatedEvent;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

  public static final String BLOG_TOPIC = "blog_created_event_topic";

  @Autowired
  private BlogRepository blogRepository;

  @Autowired
  @Qualifier("blogCreatedEventKafkaTemplate")
  private KafkaTemplate<String, BlogCreatedEvent> blogCreatedEventKafkaTemplate;

  public CompletableFuture<Blog> save(Blog blog) {

    var entity =  blogRepository.save(blog);
    var event = new BlogCreatedEvent(entity.getUserId(), entity.getTitle(), entity.getContent());
    return blogCreatedEventKafkaTemplate.send(BLOG_TOPIC, event.getUserId(), event).completable()
        .thenApply(res -> entity);
  }

  public List<Blog> findAll() {
    return blogRepository.findAll();
  }
}
