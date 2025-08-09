package com.jpa.homeworkjpa.repository;

import com.jpa.homeworkjpa.model.dto.response.ListProductResponse;
import com.jpa.homeworkjpa.model.dto.response.Pagination;
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


    public ListProductResponse findAll(Integer page, Integer size) {
        List<Product> product = em.createQuery("select p from Product p" , Product.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
        Integer totalElements = em.createQuery("SELECT p FROM Product p" , Product.class)
                .getResultList().size();

        Long totalPages = (long) Math.ceil((double) totalElements / size);

        Pagination pagination = Pagination.builder()
                .totalElements(totalElements)
                .currentPage(page)
                .pageSize(size)
                .totalPage(totalPages)
                .build();
        ListProductResponse response = ListProductResponse.builder()
                .items(product)
                .pagination(pagination)
                .build();

        return response;
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
        em.detach(product);
        // set new data
        product.setName(product_up.getName());
        product.setPrice(product_up.getPrice());
        product.setQuantity(product_up.getQuantity());
        em.merge(product);
        return product;
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
