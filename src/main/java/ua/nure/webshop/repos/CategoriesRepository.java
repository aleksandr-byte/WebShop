package ua.nure.webshop.repos;

import org.springframework.data.repository.CrudRepository;
import ua.nure.webshop.domain.Categories;

public interface CategoriesRepository extends CrudRepository<Categories, Long> {
}
