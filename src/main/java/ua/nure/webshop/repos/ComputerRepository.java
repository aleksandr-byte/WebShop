package ua.nure.webshop.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ua.nure.webshop.domain.Computer;

public interface ComputerRepository extends CrudRepository<Computer, Long> {
    Page<Computer> findAll(Pageable pageable);
}
