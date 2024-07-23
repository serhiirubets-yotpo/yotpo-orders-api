package com.example.serhii.orders.api.orders.kafka;

import com.example.serhii.orders.api.orders.dto.OrderCreatedDto;
import com.example.serhii.orders.api.orders.dto.OrderReceivedDto;
import com.example.serhii.orders.api.orders.dto.OrderKafkaDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.Properties;

@Service
public class OrderKafkaProducer {
  private final Producer kafkaProducer;
  private final ObjectMapper objectMapper;

  OrderKafkaProducer(ObjectMapper objectMapper) throws UnknownHostException {
    this.objectMapper = objectMapper;
    Properties config = new Properties();

    String kafkaBootstrapServers = System.getenv().getOrDefault("KAFKA_BOOTSTRAP_SERVERS", "localhost:9092");

    config.put("client.id", "orders-api");
    config.put("bootstrap.servers", kafkaBootstrapServers);
    config.put("key.serializer",
      "org.apache.kafka.common.serialization.StringSerializer");
    config.put("value.serializer",
      "org.apache.kafka.common.serialization.StringSerializer");
    this.kafkaProducer = new KafkaProducer<String, String>(config);
  }

  public void produceOrderReceivedEvent(OrderReceivedDto orderReceivedDto) throws JsonProcessingException {
    OrderKafkaDto<OrderReceivedDto> orderKafkaDto = new OrderKafkaDto<>(orderReceivedDto, OrderKafkaEventType.ORDER_RECEIVED);
    String orderJson = objectMapper.writeValueAsString(orderKafkaDto);

    this.kafkaProducer.send(new ProducerRecord<>("orders",
      Long.toString(orderReceivedDto.getStoreId()), orderJson));

    System.out.println("Order type 'received event' was produced with: " + orderJson);
  }

  public void produceOrderCreatedEvent(OrderCreatedDto orderCreatedDto) throws JsonProcessingException {
    OrderKafkaDto<OrderCreatedDto> orderKafkaDto = new OrderKafkaDto<>(orderCreatedDto, OrderKafkaEventType.ORDER_CREATED);
    String orderJson = objectMapper.writeValueAsString(orderKafkaDto);

    this.kafkaProducer.send(new ProducerRecord<>("orders",
      Long.toString(orderCreatedDto.getStoreId()), orderJson));

    System.out.println("Order type 'created event' was produced with: " + orderJson);
  }
}
