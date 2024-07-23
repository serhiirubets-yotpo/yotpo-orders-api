package com.example.serhii.orders.api.products;

import com.example.serhii.orders.api.orders.OrderEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "Product")
@Table(name = "products")
public class ProductEntity {
  @Id
  @SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
  @Column(name = "id")
  @NotNull
  private Long id;

  @NotNull
  @Column(name = "external_product_id")
  private String externalProductId;

  @NotNull
  @Column(name = "product_name")
  private String productName;

  @NotNull
  @Column(name = "product_description")
  private String productDescription;

  @NotNull
  @Column(name = "product_price")
  private double productPrice;

  @NotNull
  @Column(name = "quantity")
  private int quantity;

  @ManyToMany(mappedBy = "products")
  private List<OrderEntity> orders;
}
