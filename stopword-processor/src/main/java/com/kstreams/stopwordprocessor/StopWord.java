package com.kstreams.stopwordprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StopWord {

  private final Set<String> stopWords = new HashSet<>();

  @PostConstruct
  void init() {
    try {
      //File file = ResourceUtils.getFile("classpath:stopwords.txt");
      Resource resource = new ClassPathResource("data/stopwords.txt", StopWord.class.getClassLoader());
      InputStream inputStream = resource.getInputStream();
      Objects.requireNonNull(inputStream);
      String line;
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      while ((line = reader.readLine()) != null) {
        stopWords.add(line);
      }
    } catch (IOException ex) {
      log.error("Error Occurred While Reading StopWords", ex);
      throw new RuntimeException(ex);
    }
  }

  public String process(String document) {
    Set<String> wordsInDocument = Set.of(document.split(" "));
    StringBuilder builder = new StringBuilder();
    wordsInDocument.stream()
        .filter(word -> !stopWords.contains(word.toLowerCase()))
        .forEach((word) -> builder.append(word).append(" "));
    return builder.toString();
  }
}
