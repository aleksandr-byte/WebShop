package ua.nure.webshop.service;

import ua.nure.webshop.domain.Products;

import java.util.List;

public interface RecommendationService {

    List<Products> getItems(List<Products> products);

}
