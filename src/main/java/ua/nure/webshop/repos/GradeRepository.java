package ua.nure.webshop.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.nure.webshop.domain.Grade;

import java.util.Optional;

public interface GradeRepository extends CrudRepository<Grade, Long> {

    @Query(value = "SELECT avg " +
            "(grade.grade) FROM webshop.grade WHERE grade.product_id = ?1",
            nativeQuery = true)
    Optional<Integer> getAvgGradeByProductID (Long product_id);

}
