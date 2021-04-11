package com.pedro.cruzeiro.dev.inventorymanagement.controller;

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
    @APIOperation("createProduct")
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody @Valid CreateProductRequest request){
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    @APIOperation("deleteProduct")
    public ResponseEntity deleteProduct(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/product/{id}")
    @APIOperation("patchProduct")
    public ResponseEntity patchProduct(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    @APIOperation("getProduct")
    public ResponseEntity getProduct(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/product")
    @APIOperation("getProducts")
    public ResponseEntity getProducts(){
        return new ResponseEntity(HttpStatus.OK);
    }


    @PatchMapping("/product/{id}/restock")
    @APIOperation("restockProduct")
    public ResponseEntity restockProduct(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/product/{id}/status")
    @APIOperation("updateProductAvailability")
    public ResponseEntity updateProductAvailability(){
        return new ResponseEntity(HttpStatus.OK);
    }
}
