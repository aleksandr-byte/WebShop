package ua.nure.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.nure.webshop.domain.Order;
import ua.nure.webshop.domain.ProductOrder;
import ua.nure.webshop.domain.User;
import ua.nure.webshop.repos.ProductOrderRepository;
import ua.nure.webshop.service.UserService;
import ua.nure.webshop.service.impl.MailSenderServiceImpl;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller
@RequestMapping("/order")
public class OrderController {

    private ProductOrderRepository productOrderRepository;
    private UserService userService;

    @Autowired
    private MailSenderServiceImpl mailSender;

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
            user = setGuest(email);
            userService.addUser(user);
            order.setUser(user);
        }
        StringBuilder stringBuilder = new StringBuilder();
        double orderTotalPRice = 0;
        for (ProductOrder productOrder : productOrders) {
            productOrder.setOrder(order);
            stringBuilder.append("Product name: ")
                    .append(productOrder.getProducts().getName())
                    .append(", quantity: ")
                    .append(productOrder.getQuantity())
                    .append(", price: ")
                    .append(productOrder.getTotalPrice()).append("\n");
            orderTotalPRice = orderTotalPRice + productOrder.getTotalPrice().doubleValue();
            productOrderRepository.save(productOrder);
        }
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Thank you for your buying. \nOrder details: \n%s\nTotal order price: %s",
                    user.getEmail().split("@")[0],
                    stringBuilder.toString(),
                    orderTotalPRice
            );
            mailSender.send(user.getEmail(), "Order details", message);
        }
        session().removeAttribute("productOrders");
        return "users/success";
    }

    public HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true);
    }

    private User setGuest(String email) {
        User guest = new User();
        guest.setUsername(email.split("@")[0] + random(1, 1000));
        guest.setEmail(email);
        guest.setRoleID(Long.valueOf(1));
        guest.setPassword(userService.generatePassword());
        return guest;
    }

    private double random(double min, double max) {
        double res = max - min;
        Random random = new Random();
        return (min + res * random.nextDouble());
    }
}
