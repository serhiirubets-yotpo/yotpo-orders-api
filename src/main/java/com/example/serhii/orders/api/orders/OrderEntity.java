package com.example.serhii.orders.api.orders;

import com.example.serhii.orders.api.products.ProductEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "Order")
@Table(name = "orders")
public class OrderEntity {
  @Id
  @SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
  @Column(name = "id")
  @NotNull
  private Long id;

  @NotNull
  @Column(name = "shopper_email")
  private String shopperEmail;

  @NotNull
  @Column(name = "shopper_first_name")
  private String shopperFirstName;

  @Column(name = "shopper_last_name")
  private String shopperLastName;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
    name = "orders_products",
    joinColumns = @JoinColumn(name = "orders_id"),
    inverseJoinColumns = @JoinColumn(name = "products_id")
  )
  private List<ProductEntity> products = new ArrayList<>();

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @NotNull
  @Column(name = "order_date")
  private LocalDateTime orderDate;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}
