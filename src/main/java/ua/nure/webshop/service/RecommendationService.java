package ua.nure.webshop.service;

import ua.nure.webshop.domain.Products;
import ua.nure.webshop.domain.User;

import java.util.List;

public interface RecommendationService {

    List<Products> getItems(List<Products> products, User user);

}
