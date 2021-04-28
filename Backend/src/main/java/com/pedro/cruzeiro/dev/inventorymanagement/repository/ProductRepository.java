package com.pedro.cruzeiro.dev.inventorymanagement.repository;

import com.pedro.cruzeiro.dev.inventorymanagement.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
