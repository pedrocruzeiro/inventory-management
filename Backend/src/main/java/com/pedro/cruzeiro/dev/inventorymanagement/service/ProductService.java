package com.pedro.cruzeiro.dev.inventorymanagement.service;

import com.pedro.cruzeiro.dev.inventorymanagement.dto.in.CreateProductRequest;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.in.UpdateProductAvailability;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.in.UpdateProductRequest;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.CreateProductResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.GetProductResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.GetProductsResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.UpdateProductResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.exception.ProductNotFoundException;
import com.pedro.cruzeiro.dev.inventorymanagement.model.Product;
import com.pedro.cruzeiro.dev.inventorymanagement.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ModelMapper modelMapper;
  private final ProductRepository productRepository;

  public CreateProductResponse createProduct(CreateProductRequest request) {
    Product product = modelMapper.map(request, Product.class);
    return modelMapper.map(productRepository.save(product), CreateProductResponse.class);
  }

  public void deleteProduct(String productId) {
    Product product = getProductInternal(productId);
    productRepository.delete(product);
  }

  public UpdateProductResponse updateProduct(String productId, UpdateProductRequest updateProductRequest) {

    modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    Product map = modelMapper.map(updateProductRequest, Product.class);
    map.setId(productId);

    Product save = productRepository.save(map);

    return modelMapper.map(save,UpdateProductResponse.class);
  }

  private Product getProductInternal(String productId) {

    return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product doesn't exist."));
  }

  public GetProductResponse getProduct(String productId) {
    Product product = getProductInternal(productId);
    return modelMapper.map(product, GetProductResponse.class);
  }

  public void restockProduct(String productId, Integer quantity) {}

  public GetProductsResponse getProducts() {
    List<Product> products = productRepository.findAll();
    List<GetProductResponse> productList = products.stream()
            .map(product -> modelMapper.map(product, GetProductResponse.class))
            .collect(Collectors.toList());
    return GetProductsResponse.builder().count(productList.size()).products(productList).build();
  }

  public void updateProductAvailability(UpdateProductAvailability request) {}
}
