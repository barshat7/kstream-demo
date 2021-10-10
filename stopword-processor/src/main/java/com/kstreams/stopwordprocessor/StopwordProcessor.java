package com.kstreams.stopwordprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kstreams.events.BlogCreatedEvent;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StopwordProcessor {

  public static final String BLOG_CREATED_TOPIC = "blog_created_event_topic_v3";

  public static final String STOP_WORD_REMOVED_TOPIC = "stop_word_removed_topic_v3";

  @Autowired private StopWord stopWord;

  @Autowired
  public void build(StreamsBuilder builder) {
    var keySerde = Serdes.String();
    var deserializer = new JsonDeserializer<>(BlogCreatedEvent.class, false);
    JsonSerializer<BlogCreatedEvent> serializer = new JsonSerializer<>(new ObjectMapper());
    deserializer.addTrustedPackages("com.kstreams.events");
    var valueSerde = Serdes.serdeFrom(serializer, deserializer);
    builder.stream(BLOG_CREATED_TOPIC, Consumed.with(keySerde, valueSerde))
        .mapValues(this::removeStopWord)
        .to(STOP_WORD_REMOVED_TOPIC, Produced.with(keySerde, valueSerde));
  }

  BlogCreatedEvent removeStopWord(BlogCreatedEvent event) {
    String title = stopWord.process(event.getTitle());
    String content = stopWord.process(event.getContent());
    return new BlogCreatedEvent(event.getId(), event.getUserId(), title, content);
  }
}
