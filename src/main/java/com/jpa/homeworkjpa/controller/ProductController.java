package com.jpa.homeworkjpa.controller;

import com.jpa.homeworkjpa.model.dto.request.ProductRequest;
import com.jpa.homeworkjpa.model.dto.response.ApiResponse;
import com.jpa.homeworkjpa.model.dto.response.BaseResponse;
import com.jpa.homeworkjpa.model.dto.response.ListProductResponse;
import com.jpa.homeworkjpa.model.entity.Product;
import com.jpa.homeworkjpa.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Create Product")
    public ResponseEntity<ApiResponse<Product>> create(@RequestBody @Valid ProductRequest product) {
        return responseEntity(productService.save(product) , "Product saved completed" , HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "List All Product")
    public ResponseEntity<ApiResponse<ListProductResponse>> findAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return responseEntity(productService.findAll(page , size), "Fetch all product" , HttpStatus.OK);
    }
    @GetMapping("/low-stock/{quantity}")
    @Operation(summary = "Search Product Low Stock")
    public ResponseEntity<ApiResponse<List<Product>>> findLowStock(@PathVariable Integer quantity) {
        return responseEntity(productService.findProductLowStock(quantity), "Products with quantity less than 1 fetched successfully" , HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Search Product By Id")
    public ResponseEntity<ApiResponse<Product>> findById(@PathVariable Long id) {
        return responseEntity(productService.findById(id), "Product fetched successfully" , HttpStatus.OK);
    }

    @GetMapping("/by-name/{name}")
    @Operation(summary = "Search Product By Name")
    public ResponseEntity<ApiResponse<List<Product>>> findByName(@PathVariable String name) {
        return responseEntity(productService.findByName(name), "Products name " + name + " fetched successfully" , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Product")
    public ResponseEntity<ApiResponse<Product>> update(@RequestBody @Valid ProductRequest product , @PathVariable Long id) {
        return responseEntity(productService.update(product , id) , "Product updated successfully" , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Product")
    public ResponseEntity<ApiResponse<Product>> delete(@PathVariable Long id) {
        productService.delete(id);
        return responseEntity(null , "Product deleted successful" , HttpStatus.OK);
    }
}
