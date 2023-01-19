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
    return new ResponseEntity<>(
        productService.createProduct(createProductRequest), HttpStatus.CREATED);
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
  public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") String productId) {
    productService.deleteProduct(productId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/products/{id}")
  @APIOperation(GET_PRODUCT_API_OPERATION)
  @RequiredHeaders(USER_ID)
  @MdcContextHeaders(USER_ID)
  public ResponseEntity<GetProductResponse> getProduct(@PathVariable("id") String productId) {

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

  @PatchMapping("/products/{id}/stock/add/{quantity}")
  @APIOperation(ADD_STOCK_PRODUCT_API_OPERATION)
  @RequiredHeaders(USER_ID)
  @MdcContextHeaders({USER_ID})
  public ResponseEntity<HttpStatus> addStockProduct(
      @PathVariable("id") String productId, @PathVariable("quantity") Integer quantity) {

    productService.addStockProduct(productId, quantity);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PatchMapping("/products/{id}/stock/remove/{quantity}")
  @APIOperation(REMOVE_STOCK_PRODUCT_API_OPERATION)
  @RequiredHeaders(USER_ID)
  @MdcContextHeaders({USER_ID})
  public ResponseEntity<HttpStatus> removeStockProduct(
          @PathVariable("id") String productId, @PathVariable("quantity") Integer quantity) {

    productService.removeStockProduct(productId, quantity);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PatchMapping("/products/{id}/status")
  @APIOperation("updateProductAvailability")
  @RequiredHeaders(USER_ID)
  @MdcContextHeaders(USER_ID)
  public ResponseEntity<HttpStatus> updateProductAvailability(
      @PathVariable("id") String productId, @Valid UpdateProductAvailability request) {
    productService.updateProductAvailability(productId, request);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
