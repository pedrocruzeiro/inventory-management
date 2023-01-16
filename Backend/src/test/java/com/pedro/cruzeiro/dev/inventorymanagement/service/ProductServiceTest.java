package com.pedro.cruzeiro.dev.inventorymanagement.service;

import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.GetProductResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.GetProductsResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.enums.ProductStatusEnum;
import com.pedro.cruzeiro.dev.inventorymanagement.exception.ProductNotFoundException;
import com.pedro.cruzeiro.dev.inventorymanagement.model.Product;
import com.pedro.cruzeiro.dev.inventorymanagement.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {ProductService.class, ModelMapper.class})
class ProductServiceTest {

  @Autowired private ProductService productService;

  @MockBean private ProductRepository productRepository;

  private static Product IPHONE_PRODUCT;

  private static Product SAMSUNG_PRODUCT;

  @BeforeAll
  static void init() {

    IPHONE_PRODUCT =
        Product.builder()
            .id(UUID.randomUUID().toString())
            .name("Iphone 12")
            .price(BigDecimal.valueOf(499.99))
            .status(ProductStatusEnum.AVAILABLE)
            .stock(BigDecimal.valueOf(2.0))
            .build();

    SAMSUNG_PRODUCT =
        Product.builder()
            .id(UUID.randomUUID().toString())
            .name("Samsung 7")
            .price(BigDecimal.valueOf(299.99))
            .status(ProductStatusEnum.ORDERED)
            .stock(BigDecimal.valueOf(0.0))
            .build();
  }

  @Test
  void GetProduct_Success() {

    when(productRepository.findById(IPHONE_PRODUCT.getId()))
        .thenReturn(Optional.ofNullable(IPHONE_PRODUCT));
    GetProductResponse response = productService.getProduct(IPHONE_PRODUCT.getId());
    assertNotNull(response);
    assertEquals(IPHONE_PRODUCT.getName(), response.getName());
    assertEquals(new BigDecimal("499.99"), response.getPrice());
    assertEquals(ProductStatusEnum.AVAILABLE, response.getStatus());
    assertEquals(new BigDecimal("2.0"), response.getStock());
  }

  @Test
  void GetProduct_Error() {

    when(productRepository.findById("invalid_id")).thenReturn(Optional.empty());
    ProductNotFoundException productNotFoundException =
        assertThrows(ProductNotFoundException.class, () -> productService.getProduct("invalid_id"));
    assertNotNull(productNotFoundException);
    assertEquals("Product doesn't exist.", productNotFoundException.getMessage());
  }

  @Test
  void GetProducts_Success() {
    List<Product> products = Arrays.asList(IPHONE_PRODUCT, SAMSUNG_PRODUCT);
    when(productRepository.findAll()).thenReturn(products);
    GetProductsResponse response = productService.getProducts();
    assertNotNull(response);

    assertThat(response.getProducts())
        .hasSize(2)
        .extracting(GetProductResponse::getName)
        .containsExactlyInAnyOrder("Iphone 12", "Samsung 7");
  }

  @Test
  void GetProducts_EmptyList_Success() {

    when(productRepository.findAll()).thenReturn(Collections.emptyList());
    GetProductsResponse response = productService.getProducts();
    assertNotNull(response);
    assertEquals(0, response.getCount());
  }

  @Test
  void DeleteProduct_Success() {

    when(productRepository.findById(IPHONE_PRODUCT.getId()))
        .thenReturn(Optional.ofNullable(IPHONE_PRODUCT));
    productService.deleteProduct(IPHONE_PRODUCT.getId());
    verify(productRepository, times(1)).findById(IPHONE_PRODUCT.getId());
    verify(productRepository, times(1)).delete(IPHONE_PRODUCT);
  }

  @Test
  void DeleteProduct_Error() {

    when(productRepository.findById("invalid_id")).thenReturn(Optional.empty());
    ProductNotFoundException productNotFoundException =
        assertThrows(
            ProductNotFoundException.class, () -> productService.deleteProduct("invalid_id"));
    assertNotNull(productNotFoundException);
    assertEquals("Product doesn't exist.", productNotFoundException.getMessage());

    verify(productRepository, times(1)).findById("invalid_id");
    verify(productRepository, times(0)).delete(IPHONE_PRODUCT);
  }
}
