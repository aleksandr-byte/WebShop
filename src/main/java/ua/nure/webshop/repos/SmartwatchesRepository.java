package ua.nure.webshop.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ua.nure.webshop.domain.Smartwatches;

public interface SmartwatchesRepository extends CrudRepository<Smartwatches, Long> {
    Page<Smartwatches> findAll(Pageable pageable);
}
