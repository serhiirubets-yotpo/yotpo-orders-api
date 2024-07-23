package com.example.serhii.orders.api.orders;

import com.example.serhii.orders.api.orders.dto.OrderReceivedDto;
import com.example.serhii.orders.api.orders.kafka.OrderKafkaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducerService {
  private final OrderKafkaProducer orderKafkaProducer;

  public void produce(OrderReceivedDto orderReceivedDto) throws JsonProcessingException {
    this.orderKafkaProducer.produceOrderReceivedEvent(orderReceivedDto);
  }
}
