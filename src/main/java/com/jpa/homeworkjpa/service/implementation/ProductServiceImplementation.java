package com.jpa.homeworkjpa.service.implementation;

import com.jpa.homeworkjpa.exception.BadRequestException;
import com.jpa.homeworkjpa.model.dto.request.ProductRequest;
import com.jpa.homeworkjpa.model.entity.Product;
import com.jpa.homeworkjpa.repository.ProductRepository;
import com.jpa.homeworkjpa.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImplementation  implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll(Integer page , Integer size) {
        if(productRepository.findAll(page,size).isEmpty()) {
            throw new BadRequestException("Product not found");
        }
        return productRepository.findAll(page,size);
    }

    @Override
    public Product save(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        if(product == null) {
            throw new BadRequestException("This product can't save");
        }
        productRepository.save(product);
        return product;
    }

    @Override
    public Product findById(Long id) {
        Product product = productRepository.findById(id);
        if(product == null) {
            throw new BadRequestException("Product not found");
        }
        return product;
    }

    @Override
    public Product update(ProductRequest productRequest, Long id) {
        Product product = productRepository.findById(id);
        if(product == null) {
            throw new BadRequestException("Product not found");
        }
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        productRepository.update(product , id);
        return product;
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id);
        if(product == null) {
            throw new BadRequestException("Product not found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findProductLowStock(Integer quantity) {
        List<Product> products = productRepository.findProductLowStock(quantity);
        if(products == null) {
            throw new BadRequestException("Product not found");
        }
       return productRepository.findProductLowStock(quantity);
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> products = productRepository.findByName(name);
        if(products == null) {
            throw new BadRequestException("Product not found");
        }
        return productRepository.findByName(name);
    }
}
