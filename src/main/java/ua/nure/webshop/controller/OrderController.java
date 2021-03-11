package ua.nure.webshop.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.nure.webshop.domain.Order;
import ua.nure.webshop.domain.ProductOrder;
import ua.nure.webshop.domain.Role;
import ua.nure.webshop.domain.User;
import ua.nure.webshop.repos.ProductOrderRepository;
import ua.nure.webshop.service.UserService;
import ua.nure.webshop.utils.PasswordGenerator;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/order")
public class OrderController {

    private ProductOrderRepository productOrderRepository;
    private UserService userService;

    public OrderController(ProductOrderRepository productOrderRepository, UserService userService) {
        this.productOrderRepository = productOrderRepository;
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        List<ProductOrder> productOrders = (List<ProductOrder>) session().getAttribute("productOrders");
        if (productOrders != null) {
            BigDecimal totalPrice = BigDecimal.valueOf(0);
            for (ProductOrder productOrder : productOrders) {
                totalPrice = totalPrice.add(productOrder.getTotalPrice());
            }
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("cart", productOrders);
        } else {
            model.addAttribute("totalPrice", 0);
        }
        return "order/order";
    }

    @PostMapping("/buy")
    public String confirmOrder(@AuthenticationPrincipal User user,
                               @RequestParam(required = false) String cardNumber,
                               @RequestParam(required = false) String deliveryAddress,
                               @RequestParam(required = false) String email) {
        List<ProductOrder> productOrders = (List<ProductOrder>) session().getAttribute("productOrders");
        ProductOrder productOrder = productOrders.get(0);
        UUID uuid = UUID.randomUUID();
        Order order = new Order();
        order.setAddress(deliveryAddress);
        order.setCardNumber(cardNumber);
        order.setDate(LocalDateTime.now());
        order.setOrdersId(uuid.toString());
        order.setPaymentType("card");
        order.setStatus("accepted");
        if (user != null) {
            order.setUser(user);
        } else {
            User quest = setGuest(email);
            userService.addUser(quest);
            order.setUser(quest);
        }
        productOrder.setOrder(order);
        productOrderRepository.save(productOrder);
        session().removeAttribute("productOrders");
        return "users/success";
    }

    public HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true);
    }

    private User setGuest(String email) {
        User guest = new User();
        guest.setUsername(email.split("@")[0]);
        guest.setEmail(email);
        Set<Role> roleSet = new HashSet();
        roleSet.add(Role.USER);
        guest.setRoles(roleSet);
        guest.setPassword(userService.generatePassword());
        return guest;
    }
}
