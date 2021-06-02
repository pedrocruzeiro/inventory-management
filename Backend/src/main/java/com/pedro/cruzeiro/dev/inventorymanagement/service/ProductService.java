package com.pedro.cruzeiro.dev.inventorymanagement.service;

import com.pedro.cruzeiro.dev.inventorymanagement.dto.in.CreateProductRequest;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.in.UpdateProductAvailability;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.in.UpdateProductRequest;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.CreateProductResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.GetProductResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.UpdateProductResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.model.Product;
import com.pedro.cruzeiro.dev.inventorymanagement.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ModelMapper modelMapper;
	private final ProductRepository productRepository;

	public CreateProductResponse createProduct(CreateProductRequest request) {
		Product product = modelMapper.map(request,Product.class);
		return modelMapper.map(productRepository.save(product),CreateProductResponse.class);
	}

	public void deleteProduct(String productId) {
	}

	public UpdateProductResponse updateProduct(UpdateProductRequest request) {

		return UpdateProductResponse.builder()
				.build();
	}

	public GetProductResponse getProduct(String productId) {
		return GetProductResponse.builder().
				build();
	}

	public void restock(String productId, Integer quantity) {
	}

	public List<GetProductResponse> getProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(product -> modelMapper.map(product, GetProductResponse.class)).collect(Collectors.toList());
	}

	public void updateProductAvailability(UpdateProductAvailability request) {

	}
}
