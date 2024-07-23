package com.example.serhii.orders.api;

import com.example.serhii.orders.api.orders.kafka.OrderConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SerhiiOrdersApiApplication implements ApplicationListener<ContextRefreshedEvent> {

  private final OrderConsumer orderConsumer;

  public SerhiiOrdersApiApplication(OrderConsumer orderConsumer) {
    this.orderConsumer = orderConsumer;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    Thread consumerThread = new Thread(orderConsumer);
    consumerThread.start();
  }

  @GetMapping("/health")
  public String health() {
    return "OK";
  }

  public static void main(String[] args) {
    SpringApplication.run(SerhiiOrdersApiApplication.class, args);
  }
}
