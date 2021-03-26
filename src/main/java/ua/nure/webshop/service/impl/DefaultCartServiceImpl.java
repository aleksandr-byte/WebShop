package ua.nure.webshop.service.impl;

import org.springframework.stereotype.Service;
import ua.nure.webshop.builder.SqlBuilder;
import ua.nure.webshop.domain.ProductOrder;
import ua.nure.webshop.domain.Products;
import ua.nure.webshop.domain.User;
import ua.nure.webshop.repos.ProductRepository;
import ua.nure.webshop.service.CartService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultCartServiceImpl implements CartService {

    @PersistenceContext
    private EntityManager entityManager;
    private final ProductRepository productRepository;

    public DefaultCartServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProductToCart(String productID, HttpSession session) {
        HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
        if (cart != null) {
            clearCart(session);
        } else {
            cart = new HashMap();
        }
        cart.put(productID, 1);
        clearCart(session);
        session.setAttribute("cart", cart);
    }

    @Override
    public void removeProductFromCart(String productID, HttpSession session) {
        HashMap<String, Integer> cartFromSession = (HashMap<String, Integer>) session.getAttribute("cart");
        cartFromSession.remove(productID);
        session.removeAttribute("cart");
        session.setAttribute("cart", cartFromSession);
    }

    @Override
    public List<Products> getProductsFromCart(Set<String> ids) {
        SqlBuilder sqlBuilder = new SqlBuilder(ids);
        TypedQuery<Products> query = entityManager.createQuery(sqlBuilder.getQuery(), Products.class);
        return query.getResultList();
    }

    @Override
    public HashMap<String, Integer> getCartFromSession(HttpSession session) {
        HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
        return cart == null ? new HashMap() : cart;
    }

    @Override
    public void clearCart(HttpSession session) {
        session.removeAttribute("cart");
    }
}
