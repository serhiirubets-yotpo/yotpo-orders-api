package com.example.serhii.orders.api.orders.dto;

import com.example.serhii.orders.api.orders.kafka.OrderKafkaEventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class OrderKafkaDto<T> {
  private T payload;
  private OrderKafkaEventType eventType;
}
