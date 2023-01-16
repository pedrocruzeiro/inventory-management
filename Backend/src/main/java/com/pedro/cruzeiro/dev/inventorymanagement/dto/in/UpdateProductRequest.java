package com.pedro.cruzeiro.dev.inventorymanagement.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {

  private String productId;

  private String name;

  private String description;

  private String manufacturer;

  private String barcode;

  private BigDecimal price;

  private List<ProductAttributesDto> attributes;
}
