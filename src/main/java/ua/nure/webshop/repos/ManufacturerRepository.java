package ua.nure.webshop.repos;

import org.springframework.data.repository.CrudRepository;
import ua.nure.webshop.domain.Manufacturer;

public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long> {
}
