package ua.nure.webshop.repos;

import org.springframework.data.repository.CrudRepository;
import ua.nure.webshop.domain.Grade;

public interface GradeRepository extends CrudRepository<Grade, Long> {
}
