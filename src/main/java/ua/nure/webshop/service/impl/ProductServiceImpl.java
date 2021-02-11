package ua.nure.webshop.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.nure.webshop.domain.Product;
import ua.nure.webshop.repos.ProductRepository;
import ua.nure.webshop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("${images.folder.path}")
    private String folderPath;
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Product> findAllProducts(PageRequest pageRequest) {
        return setImageUrl(repository.findAll(pageRequest));
    }

    @Override
    public Page<Product> findProductsByCategoryName(PageRequest pageRequest, String categoryName) {
        return setImageUrl(repository.findAllByCategoryCategoryName(pageRequest, categoryName));
    }

    private Page<Product> setImageUrl(Page<Product> productsPage) {
        productsPage.getContent().forEach(product -> {
            product.setImageUrl(folderPath + product.getImageName());
        });
        return productsPage;
    }
}
