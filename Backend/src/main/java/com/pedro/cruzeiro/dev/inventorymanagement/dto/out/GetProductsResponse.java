package com.pedro.cruzeiro.dev.inventorymanagement.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProductsResponse {

  long count;
  List<GetProductResponse> products;
}
