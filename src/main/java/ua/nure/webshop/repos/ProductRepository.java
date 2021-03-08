package ua.nure.webshop.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.webshop.domain.Products;

public interface ProductRepository extends JpaRepository<Products, Long> {

    Page<Products> findAll(Pageable pageable);

    Page<Products> findAllByCategoryCategoryName(Pageable pageable, String categoryName);

}
