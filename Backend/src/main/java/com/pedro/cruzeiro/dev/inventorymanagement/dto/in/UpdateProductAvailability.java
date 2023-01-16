package com.pedro.cruzeiro.dev.inventorymanagement.dto.in;

import com.pedro.cruzeiro.dev.inventorymanagement.enums.ProductStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductAvailability {

  @Enumerated(EnumType.STRING)
  private ProductStatusEnum status;
}
