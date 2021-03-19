package ua.nure.webshop.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.nure.webshop.builder.SqlBuilder;
import ua.nure.webshop.domain.*;
import ua.nure.webshop.repos.ProductRepository;
import ua.nure.webshop.service.ProductService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @PersistenceContext
    private EntityManager entityManager;
    @Value("${images.path}")
    private String folderPath;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Products findProductByID(Long id) {
        Products product = productRepository.findProductsById(id);
        product.setImageUrl(folderPath + product.getImageName());
        return productRepository.findProductsById(id);
    }

    @Override
    public void createProduct(Products products) {
        productRepository.save(products);
    }

    @Override
    public void updateProduct(Products products) {
        productRepository.save(products);
    }

    @Override
    public Page<Products> findAllProducts(PageRequest pageRequest) {
        Page<Products> products = productRepository.findAll(pageRequest);
        setImageUrl(products.getContent());
        return products;
    }

/*    @Override
    public Page<Products> findProductsByCategoryName(PageRequest pageRequest) {
        Page<Products> products = productRepository.findAllByCategoryCategoryName(pageRequest);
        setImageUrl(products.getContent());
        return products;
    }*/

    public Page<Products> findProductsByCategoryAndCondition(Parameters parameters, String categoryName,
                                                             PageRequest pageRequest) {
        SqlBuilder sqlBuilder = new SqlBuilder(parameters, categoryName);
        TypedQuery<Products> query = entityManager.createQuery(sqlBuilder.getQuery(), Products.class);
        List<Products> products = query.getResultList();
        setImageUrl(products);
        return pagedList(pageRequest, products);
    }

    private void setImageUrl(List<Products> products) {
        products.forEach(computer -> {
            computer.setImageUrl(folderPath + computer.getImageName());
        });
    }

    public static <E> Page<E> pagedList(Pageable pageable, List<E> listOfEntities) {
        List<E> listToReturn = listOfEntities;
        if (pageable.isPaged()) {
            int pageSize = pageable.getPageSize();
            int currentPage = pageable.getPageNumber();
            int startItem = currentPage * pageSize;
            if (listOfEntities.size() < startItem) {
                listToReturn = Collections.emptyList();
            } else {
                int toIndex = Math.min(startItem + pageSize, listOfEntities.size());
                listToReturn = listOfEntities.subList(startItem, toIndex);
            }
        }
        return new PageImpl<>(listToReturn, pageable, listOfEntities.size());
    }
}
