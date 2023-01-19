package com.pedro.cruzeiro.dev.inventorymanagement.service;

import com.pedro.cruzeiro.dev.inventorymanagement.dto.in.CreateProductRequest;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.in.ProductAttributesDto;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.in.UpdateProductAvailability;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.in.UpdateProductRequest;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.CreateProductResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.GetProductResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.GetProductsResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.UpdateProductResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.exception.ProductNotFoundException;
import com.pedro.cruzeiro.dev.inventorymanagement.exception.ProductUnitException;
import com.pedro.cruzeiro.dev.inventorymanagement.model.Product;
import com.pedro.cruzeiro.dev.inventorymanagement.model.ProductAttributes;
import com.pedro.cruzeiro.dev.inventorymanagement.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {

  private final ModelMapper modelMapper;
  private final ProductRepository productRepository;

  public CreateProductResponse createProduct(CreateProductRequest request) {
    log.info("Received Create Product request.");
    Product product = modelMapper.map(request, Product.class);
    CreateProductResponse createProductResponse =
        modelMapper.map(productRepository.save(product), CreateProductResponse.class);
    log.info(
        "Finished Create Product request successfully. Product Id is: {}.",
        createProductResponse.getId());
    return createProductResponse;
  }

  public void deleteProduct(String productId) {
    log.info("Received Delete Product request with id {}.", productId);
    Product product = getProductInternal(productId);
    productRepository.delete(product);
    log.info("Finished Delete Product request.");
  }

  public UpdateProductResponse updateProduct(
      String productId, UpdateProductRequest updateProductRequest) {

    log.info("Received Update Product request with id {}.", productId);
    Product product = getProductInternal(productId);

    Optional.ofNullable(updateProductRequest.getName()).ifPresent(product::setName);
    Optional.ofNullable(updateProductRequest.getDescription()).ifPresent(product::setDescription);
    Optional.ofNullable(updateProductRequest.getManufacturer()).ifPresent(product::setManufacturer);
    Optional.ofNullable(updateProductRequest.getBarcode()).ifPresent(product::setBarcode);
    Optional.ofNullable(updateProductRequest.getPrice()).ifPresent(product::setPrice);
    Optional.ofNullable(updateProductRequest.getAttributes())
        .map(this::mapAttributesDtoListToAttributesList)
        .ifPresent(product::setAttributes);

    Product save = productRepository.save(product);
    log.info("Finished Update Product request successfully.");
    return modelMapper.map(save, UpdateProductResponse.class);
  }

  public GetProductResponse getProduct(String productId) {
    log.info("Received Get products request");
    Product product = getProductInternal(productId);
    log.info("Finished Get Product request.");
    return modelMapper.map(product, GetProductResponse.class);
  }

  public GetProductsResponse getProducts() {
    log.info("Received Get products request");
    List<Product> products = productRepository.findAll();
    List<GetProductResponse> productList =
        products.stream()
            .map(product -> modelMapper.map(product, GetProductResponse.class))
            .collect(Collectors.toList());
    GetProductsResponse getProductsResponse =
        GetProductsResponse.builder().count(productList.size()).products(productList).build();
    log.info(
        "Finished Get Products request. Returning {} results.", getProductsResponse.getCount());
    return getProductsResponse;
  }

  public void addStockProduct(String productId, Integer quantity) {

    log.info("Received Add Stock Product request with quantity: {}.", quantity);

    Product product = getProductInternal(productId);

    product.setStock(product.getStock().add(new BigDecimal(quantity)));

    productRepository.save(product);
    log.info("Finished Add Stock Product request.");
  }

  public void removeStockProduct(String productId, Integer quantity) {

    log.info("Received Remove Stock Product request with quantity: {}.", quantity);

    Product product = getProductInternal(productId);
    BigDecimal actualStock = product.getStock();
    if(actualStock.subtract(new BigDecimal(quantity)).compareTo(new BigDecimal(0)) < 0){
      throw new ProductUnitException("Quantity to remove is lower than actual stock.");
    }

    product.setStock(product.getStock().subtract(new BigDecimal(quantity)));

    productRepository.save(product);
    log.info("Finished Remove Stock Product request.");
  }

  public void updateProductAvailability(
      String productId, UpdateProductAvailability updateProductAvailability) {

    log.info(
        "Received Update Product Availability request with status: {}.",
        updateProductAvailability.getStatus().toString());

    Product product = getProductInternal(productId);

    product.setStatus(updateProductAvailability.getStatus());

    productRepository.save(product);
    log.info("Finished Update Product Availability request");
  }

  private Product getProductInternal(String productId) {

    return productRepository
        .findById(productId)
        .orElseThrow(() -> new ProductNotFoundException("Product doesn't exist."));
  }

  private List<ProductAttributes> mapAttributesDtoListToAttributesList(
      List<ProductAttributesDto> productAttributesDtoList) {
    return productAttributesDtoList.stream()
        .map(productAttributeDto -> modelMapper.map(productAttributeDto, ProductAttributes.class))
        .collect(Collectors.toList());
  }
}
