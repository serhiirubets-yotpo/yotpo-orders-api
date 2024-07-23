package com.example.serhii.orders.api.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class OrderCreatedDto {
  private Long storeId;
  private LocalDateTime createdAt;
  private OrderReceivedDto order;
}
