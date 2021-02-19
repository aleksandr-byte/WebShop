package ua.nure.webshop.repos;

import org.springframework.data.repository.CrudRepository;
import ua.nure.webshop.domain.Color;

public interface ColorRepository extends CrudRepository<Color, Long> {
}
