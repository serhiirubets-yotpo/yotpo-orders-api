package com.example.serhii.orders.api.orders.kafka;

import com.example.serhii.orders.api.orders.OrderEntity;
import com.example.serhii.orders.api.orders.OrderMapper;
import com.example.serhii.orders.api.orders.OrderService;
import com.example.serhii.orders.api.orders.dto.OrderKafkaDto;
import com.example.serhii.orders.api.orders.dto.OrderReceivedDto;
import com.example.serhii.orders.api.orders.dto.OrderCreatedDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

@Service
public class OrderConsumer implements Runnable {
  private final OrderService orderService;
  private KafkaConsumer<String, String> kafkaConsumer;
  private final ObjectMapper objectMapper;
  private OrderKafkaProducer orderKafkaProducer;
  private OrderMapper orderMapper;

  @Autowired
  public OrderConsumer(OrderService orderService, ObjectMapper objectMapper, OrderKafkaProducer orderKafkaProducer, OrderMapper orderMapper) {
    this.orderService = orderService;
    this.objectMapper = objectMapper;
    this.orderKafkaProducer = orderKafkaProducer;
    this.orderMapper = orderMapper;

    Properties config = this.setupConfig();

    kafkaConsumer = new KafkaConsumer<>(config);
    kafkaConsumer.subscribe(List.of("orders"));
  }

  private Properties setupConfig() {
    Properties config = new Properties();

    String kafkaBootstrapServers = System.getenv().getOrDefault("KAFKA_BOOTSTRAP_SERVERS", "localhost:9092");

    config.put("client.id", "orders-api");
    config.put("bootstrap.servers", kafkaBootstrapServers);
    config.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
    config.put("key.serializer",
      "org.apache.kafka.common.serialization.StringSerializer");
    config.put("value.serializer",
      "org.apache.kafka.common.serialization.StringSerializer");
    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

    return config;
  }

  @Override
  public void run() {
    try {
      while (true) {
        ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));

        for (ConsumerRecord<String, String> record : records) {
          try {
            TypeReference<OrderKafkaDto<OrderReceivedDto>> typeRef = new TypeReference<>() {
            };
            OrderKafkaDto<OrderReceivedDto> orderKafkaDto = objectMapper.readValue(record.value(), typeRef);

            OrderEntity order = orderMapper.toEntity(orderKafkaDto.getPayload());

            orderService.createOrder(order);

            OrderCreatedDto orderCreatedDto = new OrderCreatedDto(
              orderKafkaDto.getPayload().getStoreId(),
              order.getCreatedAt(),
              orderKafkaDto.getPayload()
            );

            this.orderKafkaProducer.produceOrderCreatedEvent(orderCreatedDto);
          } catch (Exception e) {
            System.out.println("Error parsing order: " + e.getMessage());
          }
        }
      }
    } finally {
      if (kafkaConsumer != null) {
        kafkaConsumer.close();
      }
    }
  }

}
