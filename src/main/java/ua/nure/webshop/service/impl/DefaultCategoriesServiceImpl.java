package ua.nure.webshop.service.impl;

import org.springframework.stereotype.Service;
import ua.nure.webshop.domain.Categories;
import ua.nure.webshop.repos.CategoriesRepository;
import ua.nure.webshop.service.CategoriesService;

@Service
public class DefaultCategoriesServiceImpl implements CategoriesService {

    private final CategoriesRepository repository;

    public DefaultCategoriesServiceImpl(CategoriesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Categories> findAllCategories() {
        return repository.findAll();
    }
}
