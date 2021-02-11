package ua.nure.webshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ua.nure.webshop.domain.Product;

public interface ProductService {

    Page<Product> findAllProducts(PageRequest pageRequest);

    Page<Product> findProductsByCategoryName(PageRequest pageRequest, String categoryName);
}
