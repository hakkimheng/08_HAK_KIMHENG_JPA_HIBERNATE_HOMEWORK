package com.jpa.homeworkjpa.controller;

import com.jpa.homeworkjpa.model.dto.request.ProductRequest;
import com.jpa.homeworkjpa.model.dto.response.ApiResponse;
import com.jpa.homeworkjpa.model.dto.response.BaseResponse;
import com.jpa.homeworkjpa.model.entity.Product;
import com.jpa.homeworkjpa.service.ProductService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController extends BaseResponse {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> create(@RequestBody @Valid ProductRequest product) {
        return responseEntity(productService.save(product) , "Product saved completed" , HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> findAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return responseEntity(productService.findAll(page , size), "Fetch all product" , HttpStatus.OK);
    }
    @GetMapping("/low-stock/{gty}")
    public ResponseEntity<ApiResponse<List<Product>>> findLowStock(@PathVariable Integer gty) {
        return responseEntity(productService.findProductLowStock(gty), "Fetch all product Low stock" , HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> findById(@PathVariable Long id) {
        return responseEntity(productService.findById(id), "Fetch product by id" , HttpStatus.OK);
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<ApiResponse<List<Product>>> findByName(@PathVariable String name) {
        return responseEntity(productService.findByName(name), "Fetch all product by name" , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> update(@RequestBody @Valid ProductRequest product , @PathVariable Long id) {
        return responseEntity(productService.update(product , id) , "Product updated" , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> delete(@PathVariable Long id) {
        productService.delete(id);
        return responseEntity(null , "Product has been deleted successful" , HttpStatus.OK);
    }
}
