package ua.nure.webshop.service;

import ua.nure.webshop.domain.Products;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface CartService {

    void addProductToCart(String productID, HttpSession session);

    void removeProductFromCart(String productID, HttpSession session);

    HashMap<String, Integer> getCartFromSession(HttpSession session);

    void clearCart(HttpSession session);

    List<Products> getProductsFromCart(Set<String> ids);
}
