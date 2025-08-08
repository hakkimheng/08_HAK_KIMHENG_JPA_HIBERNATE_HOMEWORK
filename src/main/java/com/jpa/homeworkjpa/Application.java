package com.jpa.homeworkjpa;

import com.jpa.homeworkjpa.model.entity.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        Product product = new Product();
        System.out.println(product.getId());
    }

}
