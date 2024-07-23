package com.example.serhii.orders.api.products;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
  ProductDto toDto(ProductEntity productEntity);

  ProductEntity toEntity(ProductDto productDto);

  @Named("toProductDtoList")
  List<ProductDto> toProductDtoList(List<ProductEntity> productEntities);

  @Named("toProductEntityList")
  List<ProductEntity> toProductEntityList(List<ProductDto> productDtos);
}
