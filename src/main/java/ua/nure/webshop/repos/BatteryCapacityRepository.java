package ua.nure.webshop.repos;

import org.springframework.data.repository.CrudRepository;
import ua.nure.webshop.domain.BatteryCapacity;

public interface BatteryCapacityRepository extends CrudRepository<BatteryCapacity, Long> {
}
