package ua.nure.webshop.service;

import org.springframework.ui.Model;
import ua.nure.webshop.domain.*;

public interface ParametersService {

    Iterable<Diagonal> finalAllDiagonals();

    Iterable<Resolution> findAllResolutions();

    Iterable<MemorySize>  findAllMemorySizes();

    Iterable<FlashMemorySize> findAllFlashMemorySizes();

    Iterable<BatteryCapacity> findAllBatteryCapacities();

    Iterable<Color> findAllColors();

    Iterable<Cpu> findAllCpus();

    Iterable<DisplayType> findAllDisplayTypes();

    Iterable<Manufacturer> findAllManufacturers();

    void setParametersToModel(Model model);

}
