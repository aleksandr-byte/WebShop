package ua.nure.webshop.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ua.nure.webshop.domain.Products;

public interface ProductRepository extends CrudRepository<Products, Long> {

    Products findProductsById(Long id);

    Page<Products> findAll(Pageable pageable);

}
