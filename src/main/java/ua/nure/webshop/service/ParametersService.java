package ua.nure.webshop.service;

import ua.nure.webshop.domain.*;

public interface ParametersService {

    Iterable<Diagonal> finalAllDiagonals();

    Iterable<Resolution> findAllResolutions();

    Iterable<MemorySize>  findAllMemorySizes();

    Iterable<FlashMemorySize> findAllFlashMemorySizes();

    Iterable<BatteryCapacity> findAllBatteryCapacities();

    Iterable<Capacity> findAllCapacities();

    Iterable<Color> findAllColors();

    Iterable<Cpu> findAllCpus();

    Iterable<DisplayType> findAllDisplayTypes();

}
