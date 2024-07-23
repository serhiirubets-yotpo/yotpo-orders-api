package com.example.serhii.orders.api.orders.dto;

import com.example.serhii.orders.api.products.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReceivedDto {
  private LocalDateTime orderDate;
  private String shopperEmail;
  private String shopperFirstName;
  private String shopperLastName;
  private Long storeId;
  private List<ProductDto> products;
}

