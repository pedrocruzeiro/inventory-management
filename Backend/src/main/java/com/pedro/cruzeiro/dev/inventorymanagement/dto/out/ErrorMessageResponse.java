package com.pedro.cruzeiro.dev.inventorymanagement.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageResponse {
  private String requestTimestamp;
  private String traceId;
  private String operation;
  private int code;
  private String message;
}
