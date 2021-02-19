package ua.nure.webshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ua.nure.webshop.domain.Computer;
import ua.nure.webshop.domain.Product;
import ua.nure.webshop.domain.Smartphone;
import ua.nure.webshop.domain.Smartwatches;

public interface ProductService {

    Page<Product> findAllProducts(PageRequest pageRequest);

    Page<Product> findProductsByCategoryName(PageRequest pageRequest, String categoryName);

    Page<Smartphone> findAllSmartphones(PageRequest pageRequest);

    Page<Computer> findAllComputers(PageRequest pageRequest);

    Page<Smartwatches> findAllSmartwatches(PageRequest pageRequest);
}
