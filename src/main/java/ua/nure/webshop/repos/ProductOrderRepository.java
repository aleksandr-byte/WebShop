package ua.nure.webshop.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.nure.webshop.domain.ProductOrder;

import java.util.List;

public interface ProductOrderRepository extends CrudRepository<ProductOrder, Long> {
}
