package com.pedro.cruzeiro.dev.inventorymanagement.controller;

import static com.pedro.cruzeiro.dev.inventorymanagement.util.constant.InventoryManagementConstants.CREATE_PRODUCT_API_OPERATION;
import static com.pedro.cruzeiro.dev.inventorymanagement.util.constant.InventoryManagementConstants.DELETE_PRODUCT_API_OPERATION;
import static com.pedro.cruzeiro.dev.inventorymanagement.util.constant.InventoryManagementConstants.GET_PRODUCTS_API_OPERATION;
import static com.pedro.cruzeiro.dev.inventorymanagement.util.constant.InventoryManagementConstants.GET_PRODUCT_API_OPERATION;
import static com.pedro.cruzeiro.dev.inventorymanagement.util.constant.InventoryManagementConstants.RESTOCK_PRODUCT_API_OPERATION;
import static com.pedro.cruzeiro.dev.inventorymanagement.util.constant.InventoryManagementConstants.UPDATE_PRODUCT_API_OPERATION;

import com.pedro.cruzeiro.dev.inventorymanagement.dto.in.CreateProductRequest;
import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.CreateProductResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.util.annotation.APIOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/v1")
public class ProductController {

    @PostMapping(value = "/product", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIOperation(CREATE_PRODUCT_API_OPERATION)
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody @Valid CreateProductRequest request){
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    @APIOperation(DELETE_PRODUCT_API_OPERATION)
    public ResponseEntity deleteProduct(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/product/{id}")
    @APIOperation(UPDATE_PRODUCT_API_OPERATION)
    public ResponseEntity updateProduct(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    @APIOperation(GET_PRODUCT_API_OPERATION)
    public ResponseEntity getProduct(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/product")
    @APIOperation(GET_PRODUCTS_API_OPERATION)
    public ResponseEntity getProducts(){
        return new ResponseEntity(HttpStatus.OK);
    }


    @PatchMapping("/product/{id}/restock")
    @APIOperation(RESTOCK_PRODUCT_API_OPERATION)
    public ResponseEntity restockProduct(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/product/{id}/status")
    @APIOperation("updateProductAvailability")
    public ResponseEntity updateProductAvailability(){
        return new ResponseEntity(HttpStatus.OK);
    }
}
