package com.example.serhii.orders.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;

public class JacksonConfig {
  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper()
      .registerModule(new Jdk8Module())
      .registerModule(new JavaTimeModule());
  }
}
