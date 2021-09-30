package com.kstreams.stopwordprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@EnableKafkaStreams
@SpringBootApplication
public class StopwordProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(StopwordProcessorApplication.class, args);
	}

}
