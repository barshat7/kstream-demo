package com.kstreams.elasticsearchwriter;

import com.kstreams.events.BlogCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BlogEventListener {

  private static final String topic = "stop_word_removed_topic";

  @KafkaListener(topics = topic)
  public void on(@Payload BlogCreatedEvent event) {
    log.info(
        "Got Event UserID: {} Title: {} Content {}",
        event.getUserId(),
        event.getTitle(),
        event.getContent());
  }
}
