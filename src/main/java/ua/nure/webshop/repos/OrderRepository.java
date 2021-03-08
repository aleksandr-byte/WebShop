package ua.nure.webshop.repos;

import org.springframework.data.repository.CrudRepository;
import ua.nure.webshop.domain.Order;

public interface OrderRepository extends CrudRepository<Order, String> {
}
