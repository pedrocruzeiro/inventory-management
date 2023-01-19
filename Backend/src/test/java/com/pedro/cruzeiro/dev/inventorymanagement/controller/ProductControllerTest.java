package com.pedro.cruzeiro.dev.inventorymanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.in.CreateProductRequest;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.CreateProductResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.GetProductResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.enums.ProductStatusEnum;
import com.pedro.cruzeiro.dev.inventorymanagement.exception.ProductNotFoundException;
import com.pedro.cruzeiro.dev.inventorymanagement.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ProductControllerTest {

  private static ObjectMapper objectMapper;
  @Autowired private MockMvc mockMvc;
  @MockBean private ProductService productService;

  @BeforeAll
  static void init() {
    objectMapper = new ObjectMapper();
  }

  @Test
  void CreateProduct_ShouldReturn_Created() throws Exception {

    CreateProductRequest createProductRequest =
        CreateProductRequest.builder()
            .name("Iphone 12 Pro")
            .description("New iphone!!")
            .manufacturer("Apple")
            .price(new BigDecimal("499.00"))
            .barcode("12312-asdas-123132")
            .status(ProductStatusEnum.AVAILABLE)
            .stock(new BigDecimal("2.0"))
            .build();

    String json = objectMapper.writeValueAsString(createProductRequest);

    when(productService.createProduct(any())).thenReturn(CreateProductResponse.builder().build());
    this.mockMvc
        .perform(post("/v1/products").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andDo(print())
        .andExpect(status().isCreated());
  }

  @Test
  void CreateProduct_ShouldReturn_NotFound() throws Exception {

    CreateProductRequest createProductRequest =
        CreateProductRequest.builder()
            .name("Iphone 12 Pro")
            .description("New iphone!!")
            .manufacturer("Apple")
            .price(new BigDecimal("499.00"))
            .barcode("12312-asdas-123132")
            .status(ProductStatusEnum.AVAILABLE)
            .stock(new BigDecimal("2.0"))
            .build();

    String json = objectMapper.writeValueAsString(createProductRequest);

    when(productService.createProduct(any()))
        .thenThrow(new ProductNotFoundException("Product doesn't exist."));
    this.mockMvc
        .perform(post("/v1/products").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Product doesn't exist."));
  }

  @Test
  void GetProductById_ShouldReturn_Product() throws Exception {

    String id = UUID.randomUUID().toString();

    GetProductResponse response =
        GetProductResponse.builder()
            .id(id)
            .name("Iphone 12 Pro")
            .description("New Iphone!!")
            .manufacturer("Apple")
            .build();

    when(productService.getProduct(id)).thenReturn(response);

    String jsonResult =
        this.mockMvc
            .perform(get("/v1/products/{id}", id))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    GetProductResponse getProductResponse =
        objectMapper.readValue(jsonResult, GetProductResponse.class);

    assertEquals("Iphone 12 Pro", getProductResponse.getName());
    assertEquals("New Iphone!!", getProductResponse.getDescription());
    assertEquals("Apple", getProductResponse.getManufacturer());
  }

  @Test
  void GetProductById_ShouldReturn_NotFound() throws Exception {

    String id = UUID.randomUUID().toString();

    when(productService.getProduct(id))
        .thenThrow(new ProductNotFoundException("Product doesn't exist."));
    this.mockMvc
        .perform(get("/v1/products/{id}", id))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Product doesn't exist."));
  }
}
