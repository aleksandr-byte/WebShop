package ua.nure.webshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.webshop.chainresp.RecommendationByAvgRatingPrice;
import ua.nure.webshop.chainresp.RecommendationByUserOrders;
import ua.nure.webshop.domain.Products;
import ua.nure.webshop.domain.User;
import ua.nure.webshop.repos.OrderRepository;
import ua.nure.webshop.service.RecommendationService;
import ua.nure.webshop.chainresp.Recommendation;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Products> getItems(List<Products> products, User user) {

        Recommendation recommendation = new RecommendationByAvgRatingPrice(new RecommendationByUserOrders());

        products = recommendation.setRecommendation(products, user, orderRepository);

        return products;
    }
}
