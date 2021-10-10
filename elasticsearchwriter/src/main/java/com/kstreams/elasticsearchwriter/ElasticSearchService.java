package com.kstreams.elasticsearchwriter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElasticSearchService {

  private final ElasticsearchOperations operations;

  public String save(BlogESEntity entity) {
    IndexQuery query =
        new IndexQueryBuilder().withId(String.valueOf(entity.getId())).withObject(entity).build();
    return operations.index(query, operations.getIndexCoordinatesFor(BlogESEntity.class));
  }
}
