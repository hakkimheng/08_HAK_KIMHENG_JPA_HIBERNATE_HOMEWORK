package com.jpa.homeworkjpa.repository;

import com.jpa.homeworkjpa.model.dto.request.ProductRequest;
import com.jpa.homeworkjpa.model.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class ProductRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Product request) {
        em.persist(request);
    }
    public List<Product> findAll(Integer page, Integer size) {
        return em.createQuery("select p from Product p" , Product.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    public Product findById(Long id) {
        Product product =  em.find(Product.class, id);
        return product;
    }

    public void deleteById(Long id) {
        em.remove(em.find(Product.class, id));
    }
    public Product update(Product product_up , Long id) {
        Product product = em.find(Product.class, id);
        em.merge(product);
        em.persist(product_up);
        return product_up;
    }

    public List<Product> findByName(String name) {
        List<Product> products = em.createQuery("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(:name)" , Product.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
        return products;
    }

    public List<Product> findProductLowStock(Integer qty) {
        List<Product> products = em.createQuery("SELECT p FROM Product p WHERE p.quantity < :qty " , Product.class)
                .setParameter("qty" , qty)
                .getResultList();
        return products;
    }
}
