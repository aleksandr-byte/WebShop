package ua.nure.webshop.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.nure.webshop.domain.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, String> {

    @Query(value = "SELECT orders.orders_id FROM orders WHERE orders.user_id = ?1",
            nativeQuery = true)
    List<String> findOrdersByUserID(Long userID);


    @Query(value = "SELECT name FROM products " +
            "INNER JOIN product_orders ON products.product_id = product_orders.product_id " +
            "WHERE product_orders.orders_id = ?1",
            nativeQuery = true)
    List<String> findOrderProductsByOrderID(String orderID);

}
