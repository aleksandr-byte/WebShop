package ua.nure.webshop.service;

import ua.nure.webshop.domain.Categories;

import java.util.List;

public interface CategoriesService {

    Iterable<Categories> findAllCategories();

}
