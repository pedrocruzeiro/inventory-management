package com.pedro.cruzeiro.dev.inventorymanagement.dto.out;

import com.pedro.cruzeiro.dev.inventorymanagement.enums.ProductStatusEnum;
import com.pedro.cruzeiro.dev.inventorymanagement.model.ProductAttributes;
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
public class GetProductsResponse {

  long count;
  List<GetProductResponse> products;
}
