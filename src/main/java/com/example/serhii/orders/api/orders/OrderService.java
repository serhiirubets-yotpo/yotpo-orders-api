package com.example.serhii.orders.api.orders;

import com.example.serhii.orders.api.products.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class OrderService {
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;

  public void createOrder(OrderEntity orderEntity) {
    this.orderRepository.save(orderEntity);
  }
}
