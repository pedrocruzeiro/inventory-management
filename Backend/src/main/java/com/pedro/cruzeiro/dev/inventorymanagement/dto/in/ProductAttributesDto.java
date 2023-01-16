package com.pedro.cruzeiro.dev.inventorymanagement.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttributesDto {

  String attribute;
  String value;
}
