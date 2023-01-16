package com.pedro.cruzeiro.dev.inventorymanagement.controller;

import com.pedro.cruzeiro.dev.inventorymanagement.dto.in.CreateProductRequest;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.in.UpdateProductAvailability;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.in.UpdateProductRequest;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.CreateProductResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.GetProductResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.GetProductsResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.UpdateProductResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.service.ProductService;
import com.pedro.cruzeiro.dev.inventorymanagement.util.annotation.APIOperation;
import com.pedro.cruzeiro.dev.inventorymanagement.util.annotation.MdcContextHeaders;
import com.pedro.cruzeiro.dev.inventorymanagement.util.annotation.RequiredHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.pedro.cruzeiro.dev.inventorymanagement.util.constant.InventoryManagementConstants.*;

@Controller
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

  private final ProductService productService;

  @PostMapping(
      value = "/products",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @APIOperation(CREATE_PRODUCT_API_OPERATION)
  @RequiredHeaders(USER_ID)
  @MdcContextHeaders(USER_ID)
  @CrossOrigin
  public ResponseEntity<CreateProductResponse> createProduct(
      @RequestBody @Valid CreateProductRequest createProductRequest) {
    return new ResponseEntity<>(productService.createProduct(createProductRequest), HttpStatus.OK);
  }

  @PatchMapping("/products/{id}")
  @APIOperation(UPDATE_PRODUCT_API_OPERATION)
  @RequiredHeaders(USER_ID)
  @MdcContextHeaders(USER_ID)
  public ResponseEntity<UpdateProductResponse> updateProduct(
      @PathVariable("id") String productId,
      @RequestBody @Valid UpdateProductRequest updateProductRequest) {
    return new ResponseEntity<>(
        productService.updateProduct(productId, updateProductRequest), HttpStatus.OK);
  }

  @DeleteMapping("/products/{id}")
  @APIOperation(DELETE_PRODUCT_API_OPERATION)
  @RequiredHeaders(USER_ID)
  @MdcContextHeaders(USER_ID)
  public ResponseEntity deleteProduct(@PathVariable("id") String productId) {
    productService.deleteProduct(productId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/products/{id}")
  @APIOperation(GET_PRODUCT_API_OPERATION)
  @RequiredHeaders(USER_ID)
  @MdcContextHeaders(USER_ID)
  public ResponseEntity getProduct(@PathVariable("id") String productId) {

    return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
  }

  @GetMapping("/products")
  @CrossOrigin
  @APIOperation(GET_PRODUCTS_API_OPERATION)
  @RequiredHeaders(USER_ID)
  @MdcContextHeaders(USER_ID)
  public ResponseEntity<GetProductsResponse> getProducts() {

    return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
  }

  @PatchMapping("/products/{id}/restock/{quantity}")
  @APIOperation(RESTOCK_PRODUCT_API_OPERATION)
  @RequiredHeaders(USER_ID)
  @MdcContextHeaders({USER_ID})
  public ResponseEntity restockProduct(
      @PathVariable("id") String productId, @PathVariable("quantity") Integer quantity) {

    productService.restockProduct(productId, quantity);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PatchMapping("/products/{id}/status")
  @APIOperation("updateProductAvailability")
  @RequiredHeaders(USER_ID)
  @MdcContextHeaders(USER_ID)
  public ResponseEntity updateProductAvailability(
      @PathVariable("id") String productId, @Valid UpdateProductAvailability request) {

    productService.updateProductAvailability(request);

    return new ResponseEntity(HttpStatus.OK);
  }
}
