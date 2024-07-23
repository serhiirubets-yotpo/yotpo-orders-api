package com.example.serhii.orders.api.orders;

import com.example.serhii.orders.api.orders.dto.OrderReceivedDto;
import com.example.serhii.orders.api.products.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = ProductMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
  OrderReceivedDto toDto(OrderEntity orderEntity);

  OrderEntity toEntity(OrderReceivedDto orderReceivedDto);
}
