package ua.nure.webshop.repos;

import org.springframework.data.repository.CrudRepository;
import ua.nure.webshop.domain.ProductOrder;

public interface ProductOrderRepository extends CrudRepository<ProductOrder, Long> {
}
