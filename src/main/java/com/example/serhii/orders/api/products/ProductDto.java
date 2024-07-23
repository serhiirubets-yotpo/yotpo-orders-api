package com.example.serhii.orders.api.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
  private Long externalProductId;
  private String productName;
  private String productDescription;
  private Long productPrice;
  private Integer quantity;
}
