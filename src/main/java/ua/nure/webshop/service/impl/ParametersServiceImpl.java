package ua.nure.webshop.service.impl;

import org.springframework.stereotype.Service;
import ua.nure.webshop.domain.*;
import ua.nure.webshop.repos.*;
import ua.nure.webshop.service.ParametersService;

@Service
public class ParametersServiceImpl implements ParametersService {

    private final DiagonalRepository diagonalRepository;
    private final ResolutionRepository resolutionRepository;
    private final MemoryRepository memoryRepository;
    private final FlashMemorySizeRepository flashMemorySizeRepository;
    private final BatteryCapacityRepository batteryCapacityRepository;
    private final CapacityRepository capacityRepository;
    private final ColorRepository colorRepository;
    private final CpuRepository cpuRepository;
    private final DisplayTypeRepository displayTypeRepository;

    public ParametersServiceImpl(DiagonalRepository diagonalRepository, ResolutionRepository resolutionRepository, MemoryRepository memoryRepository, FlashMemorySizeRepository flashMemorySizeRepository, BatteryCapacityRepository batteryCapacityRepository, CapacityRepository capacityRepository, ColorRepository colorRepository, CpuRepository cpuRepository, DisplayTypeRepository displayTypeRepository) {
        this.diagonalRepository = diagonalRepository;
        this.resolutionRepository = resolutionRepository;
        this.memoryRepository = memoryRepository;
        this.flashMemorySizeRepository = flashMemorySizeRepository;
        this.batteryCapacityRepository = batteryCapacityRepository;
        this.capacityRepository = capacityRepository;
        this.colorRepository = colorRepository;
        this.cpuRepository = cpuRepository;
        this.displayTypeRepository = displayTypeRepository;
    }

    @Override
    public Iterable<Diagonal> finalAllDiagonals() {
        return diagonalRepository.findAll();
    }

    @Override
    public Iterable<Resolution> findAllResolutions() {
        return resolutionRepository.findAll();
    }

    @Override
    public Iterable<MemorySize> findAllMemorySizes() {
        return memoryRepository.findAll();
    }

    @Override
    public Iterable<FlashMemorySize> findAllFlashMemorySizes() {
        return flashMemorySizeRepository.findAll();
    }

    @Override
    public Iterable<BatteryCapacity> findAllBatteryCapacities() {
        return batteryCapacityRepository.findAll();
    }

    @Override
    public Iterable<Capacity> findAllCapacities() {
        return capacityRepository.findAll();
    }

    @Override
    public Iterable<Color> findAllColors() {
        return colorRepository.findAll();
    }

    @Override
    public Iterable<Cpu> findAllCpus() {
        return cpuRepository.findAll();
    }

    @Override
    public Iterable<DisplayType> findAllDisplayTypes() {
        return displayTypeRepository.findAll();
    }


}
