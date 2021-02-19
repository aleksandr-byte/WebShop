package ua.nure.webshop.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ua.nure.webshop.domain.Smartphone;

public interface SmartphoneRepository extends CrudRepository<Smartphone, Long> {
    Page<Smartphone> findAll(Pageable pageable);
}
