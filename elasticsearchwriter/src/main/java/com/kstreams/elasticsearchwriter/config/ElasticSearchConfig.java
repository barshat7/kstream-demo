package com.kstreams.elasticsearchwriter.config;

import java.time.Duration;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.http.HttpHeaders;

@Configuration
@EnableElasticsearchRepositories(basePackages = {"com.kstreams.elasticsearchwriter"})
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

  @Value("${elasticsearch.username}")
  private String username;

  @Value("${elasticsearch.password}")
  private String password;

  @Override
  @Bean
  public RestHighLevelClient elasticsearchClient() {

//    HttpHeaders headers = new HttpHeaders();
//    headers.setBasicAuth("s6um73reh4", "6y9hirn5qp");
    final ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo("kafka-java-4632735100.ap-southeast-2.bonsaisearch.net:443")
        .usingSsl().withBasicAuth(username, password).withSocketTimeout(Duration.ofMinutes(10))
        .build();

    return RestClients.create(clientConfiguration).rest();
  }

  @Bean
  public ElasticsearchOperations elasticsearchOperations() {
    return new ElasticsearchRestTemplate(elasticsearchClient());
  }
}
