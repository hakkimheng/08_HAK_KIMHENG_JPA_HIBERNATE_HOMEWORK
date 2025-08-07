package com.jpa.homeworkjpa.service;

import com.jpa.homeworkjpa.model.dto.request.ProductRequest;
import com.jpa.homeworkjpa.model.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll(Integer page, Integer size);
    public Product save(ProductRequest productRequest);
    public Product findById(Long id);
    public Product update(ProductRequest productRequest , Long id);
    public void delete(Long id);
    public List<Product> findProductLowStock(Integer quantity);
    public List<Product> findByName(String name);
}
