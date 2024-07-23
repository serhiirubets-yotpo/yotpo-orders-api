package com.example.serhii.orders.api.orders;

import com.example.serhii.orders.api.orders.dto.OrderReceivedDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
  private final OrderProducerService orderProducerService;

  @PostMapping("stores/{storeId}")
  public void produceOrder(@RequestBody OrderReceivedDto orderReceivedDto, @PathVariable Long storeId) throws JsonProcessingException {
    orderReceivedDto.setStoreId(storeId);
    orderProducerService.produce(orderReceivedDto);
  }
}
