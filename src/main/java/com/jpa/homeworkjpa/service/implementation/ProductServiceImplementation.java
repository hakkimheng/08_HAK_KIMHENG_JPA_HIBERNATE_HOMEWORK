package com.jpa.homeworkjpa.service.implementation;

import com.jpa.homeworkjpa.exception.BadRequestException;
import com.jpa.homeworkjpa.exception.NotFoundException;
import com.jpa.homeworkjpa.model.dto.request.ProductRequest;
import com.jpa.homeworkjpa.model.dto.response.ListProductResponse;
import com.jpa.homeworkjpa.model.entity.Product;
import com.jpa.homeworkjpa.repository.ProductRepository;
import com.jpa.homeworkjpa.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImplementation  implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ListProductResponse findAll(Integer page , Integer size) {
        if(productRepository.findAll(page,size)==null) {
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
       return Optional.ofNullable(productRepository.findById(id))
               .orElseThrow(() -> new NotFoundException("Product with id "+ id +" found"));
    }

    @Override
    public Product update(ProductRequest productRequest, Long id) {
        Product product = Optional.ofNullable(productRepository.findById(id))
                        .orElseThrow(() -> new NotFoundException("Product with id " + id + " not found"));
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        productRepository.update(product , id);
        return product;
    }

    @Override
    public void delete(Long id) {
        Optional.ofNullable(productRepository.findById(id))
                        .orElseThrow(() -> new NotFoundException("Product with id " + id + " not found"));
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findProductLowStock(Integer quantity) {
        return Optional.ofNullable(productRepository.findProductLowStock(quantity))
                .orElseThrow(() -> new NotFoundException("Product with quantity " + quantity + " not found"));
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> products = productRepository.findByName(name);

//        if(products == null || products.isEmpty()) {
//            throw new BadRequestException("Product with name " + name + " not found");
//        }
        return products;
    }
}
