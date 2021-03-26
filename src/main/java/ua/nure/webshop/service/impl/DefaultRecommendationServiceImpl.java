package ua.nure.webshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.webshop.domain.Order;
import ua.nure.webshop.domain.Products;
import ua.nure.webshop.domain.Role;
import ua.nure.webshop.domain.User;
import ua.nure.webshop.recomendation.Recommendation;
import ua.nure.webshop.repos.OrderRepository;
import ua.nure.webshop.repos.ProductOrderRepository;
import ua.nure.webshop.service.ProductService;
import ua.nure.webshop.service.RecommendationService;
import ua.nure.webshop.utilityfunction.UtilityFunction;
import ua.nure.webshop.utilityfunction.model.FunctionUtility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultRecommendationServiceImpl implements RecommendationService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;

    @Override
    public List<Products> getItems(List<Products> products, User user) {

        List<FunctionUtility> functionUtilities = UtilityFunction.getLocalUtility(products);

        Comparator<FunctionUtility> comparator = Comparator
                .comparing(FunctionUtility::getK)
                .reversed();

        List<FunctionUtility> sortedFunctionUtilities = functionUtilities.stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        List<Products> recItem = new ArrayList();
        sortedFunctionUtilities.forEach(functionUtility -> recItem.add(functionUtility.getProduct()));
        System.out.println("///////////////////////////////////////////////////");
        System.out.println("Products rec. by def(AVG price and min price)");
        System.out.println("----------------------------------------------------");
        recItem.forEach(product -> System.out.println(product.getName()));
        System.out.println("----------------------------------------------------");

        if(user != null && user.getRoles().contains(Role.USER)){
            List<String> productsName = new ArrayList();
            List<String> ordersID = orderRepository.findOrdersByUserID(user.getId());
            for(String orderID: ordersID){
                orderRepository.findOrderProductsByOrderID(orderID).forEach(productName -> productsName.add(productName));
            }
            StringBuilder query = new StringBuilder();
            for (String name: productsName) {
                query.append(name).append(" ");
            }

            Recommendation recommendation = new Recommendation();
            products = recommendation.getRecommendation(query.toString(), recItem);
            System.out.println("///////////////////////////////////////////////////");
            System.out.println("Products rec. by client preferences");
            System.out.println("----------------------------------------------------");
            products.forEach(product -> System.out.println(product.getName() + " = " + product.getCosineSimilarity()));
            System.out.println("----------------------------------------------------");
            return products;
        }
        return recItem;
    }
}
