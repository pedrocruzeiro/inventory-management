package com.pedro.cruzeiro.dev.inventorymanagement.service;

import com.pedro.cruzeiro.dev.inventorymanagement.dto.in.CreateProductRequest;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.in.UpdateProductAvailability;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.in.UpdateProductRequest;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.CreateProductResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.GetProductResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.UpdateProductResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.criteria.CriteriaBuilder.In;


public class ProductService {

	public CreateProductResponse createProduct(CreateProductRequest request) {

		return CreateProductResponse.builder().
				productId(UUID.randomUUID().toString()).
				build();
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

	public void restock(String productId ,Integer quantity) {
	}

	public List<GetProductResponse> getProducts() {

		ArrayList response = new ArrayList<GetProductResponse>();

	return response;
	}

	public void updateProductAvailability(UpdateProductAvailability request) {

	}
}
