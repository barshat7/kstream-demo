package com.kstreams.elasticsearchwriter.config;

import com.kstreams.events.BlogCreatedEvent;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig {

  @Value("${spring.application.name}")
  private String applicationName;

  @Bean
  public ConsumerFactory<String, BlogCreatedEvent> consumerFactory() {
    Map<String, Object> config = new HashMap<>();
    var deserializer = new JsonDeserializer<BlogCreatedEvent>();
    deserializer.addTrustedPackages("com.kstreams.events");
    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
    config.put(ConsumerConfig.GROUP_ID_CONFIG, applicationName);
    return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, BlogCreatedEvent>
      kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, BlogCreatedEvent> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}
