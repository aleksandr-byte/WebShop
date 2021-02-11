package ua.nure.webshop.repos;

import org.springframework.data.repository.CrudRepository;
import ua.nure.webshop.domain.MemorySize;

public interface MemoryRepository extends CrudRepository<MemorySize, Long> {
}
