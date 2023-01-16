package com.pedro.cruzeiro.dev.inventorymanagement.exception;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(String message) {
    super(message);
  }
}
