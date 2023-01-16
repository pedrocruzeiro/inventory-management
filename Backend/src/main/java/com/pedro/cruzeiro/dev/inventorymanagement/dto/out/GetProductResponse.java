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
public class GetProductResponse {

  private String id;
  private String name;
  private String description;
  private String manufacturer;
  private String productId;
  private String barcode;
  private BigDecimal price;
  private List<ProductAttributes> attributes;
  private BigDecimal stock;
  private ProductStatusEnum status;
}
