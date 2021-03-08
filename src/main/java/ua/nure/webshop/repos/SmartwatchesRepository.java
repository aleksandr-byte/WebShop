package ua.nure.webshop.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ua.nure.webshop.domain.Smartwatch;

public interface SmartwatchesRepository extends CrudRepository<Smartwatch, Long> {
    Page<Smartwatch> findAll(Pageable pageable);
}
