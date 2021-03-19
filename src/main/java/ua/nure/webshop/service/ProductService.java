package ua.nure.webshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ua.nure.webshop.domain.*;

public interface ProductService {

    Products findProductByID(Long id);

    void createProduct(Products products);

    void updateProduct(Products product);

    Page<Products> findAllProducts(PageRequest pageRequest);

/*    Page<Products> findProductsByCategoryName(PageRequest pageRequest);*/

    Page<Products> findProductsByCategoryAndCondition(Parameters parameters, String categoryName,
                                                      PageRequest pageRequest);
}
