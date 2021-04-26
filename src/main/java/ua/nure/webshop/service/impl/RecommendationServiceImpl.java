package ua.nure.webshop.service.impl;

import org.springframework.stereotype.Service;
import ua.nure.webshop.chainresp.RecommendationByAvgRatingPrice;
import ua.nure.webshop.chainresp.RecommendationByUserOrders;
import ua.nure.webshop.chainresp.StartChain;
import ua.nure.webshop.domain.Products;
import ua.nure.webshop.domain.User;
import ua.nure.webshop.service.RecommendationService;
import ua.nure.webshop.chainresp.Recommendation;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Override
    public List<Products> getItems(List<Products> productsForQuery, User user, List<Products> productsToCompare) {

        Recommendation recommendation = new StartChain(new RecommendationByAvgRatingPrice(new RecommendationByUserOrders()));

        List<Products> products = recommendation.setRecommendation(productsForQuery, user, productsToCompare);

        return products;
    }
}
