package ua.nure.webshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.nure.webshop.domain.ProductOrder;
import ua.nure.webshop.domain.Products;
import ua.nure.webshop.service.CartService;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping()
    public String index(Model model) {
        HashMap<String, Integer> cartFromSession = cartService.getCartFromSession(session());
        if (cartFromSession.size() == 0) {
            model.addAttribute("productOrders", null);
        } else {
            List<Products> products = cartService.getProductsFromCart(cartFromSession.keySet());
            List<ProductOrder> productOrders = getListProductOrders(products, cartFromSession);
            session().removeAttribute("productOrders");
            session().setAttribute("productOrders", productOrders);
            model.addAttribute("productOrders", productOrders);
        }
        return "cart/cart";
    }

    @GetMapping("/changequantity/{id}/{quantity}")
    public String changeQuantity(Model model, @PathVariable String id, @PathVariable String quantity) {
        HashMap<String, Integer> cartFromSession = cartService.getCartFromSession(session());
        cartFromSession.put(id, Integer.parseInt(quantity));
        session().removeAttribute("cart");
        session().setAttribute("cart", cartFromSession);
        return index(model);
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable String id) {
        cartService.removeProductFromCart(id, session());
        return "redirect:/cart";
    }

    @GetMapping("/removeall")
    public String removeAll() {
        cartService.clearCart(session());
        return "redirect:/cart";
    }

    @GetMapping("/confirmorder")
    public String confirmOrder() {
        cartService.clearCart(session());
        return "redirect:/order";
    }

    private List<ProductOrder> getListProductOrders(List<Products> products, HashMap<String, Integer> cartFromSession){
        List<ProductOrder> productOrders = new ArrayList();
        for (Products product : products) {
            ProductOrder productOrder = new ProductOrder();
            productOrder.setProduct(product);
            int quantity = cartFromSession.get(String.valueOf(product.getId()));
            productOrder.setQuantity(quantity);
            productOrder.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
            productOrders.add(productOrder);
        }
        return productOrders;
    }

    private HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true);
    }
}
