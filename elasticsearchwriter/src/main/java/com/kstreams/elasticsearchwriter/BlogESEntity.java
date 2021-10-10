package com.kstreams.elasticsearchwriter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "r_blogs", createIndex = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogESEntity {

  @Id
  private Long id;

  private String title;

  private String content;
}
