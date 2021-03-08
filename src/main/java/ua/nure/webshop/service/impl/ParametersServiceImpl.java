package ua.nure.webshop.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
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
    private final ColorRepository colorRepository;
    private final CpuRepository cpuRepository;
    private final DisplayTypeRepository displayTypeRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ParametersServiceImpl(DiagonalRepository diagonalRepository, ResolutionRepository resolutionRepository, MemoryRepository memoryRepository, FlashMemorySizeRepository flashMemorySizeRepository, BatteryCapacityRepository batteryCapacityRepository, ColorRepository colorRepository, CpuRepository cpuRepository, DisplayTypeRepository displayTypeRepository, ManufacturerRepository manufacturerRepository) {
        this.diagonalRepository = diagonalRepository;
        this.resolutionRepository = resolutionRepository;
        this.memoryRepository = memoryRepository;
        this.flashMemorySizeRepository = flashMemorySizeRepository;
        this.batteryCapacityRepository = batteryCapacityRepository;
        this.colorRepository = colorRepository;
        this.cpuRepository = cpuRepository;
        this.displayTypeRepository = displayTypeRepository;
        this.manufacturerRepository = manufacturerRepository;
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

    @Override
    public Iterable<Manufacturer> findAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    @Override
    public void setParametersToModel(Model model){
        Iterable<Diagonal> diagonals = diagonalRepository.findAll();
        model.addAttribute("diagonals", diagonals);

        Iterable<Resolution> resolutions = resolutionRepository.findAll();
        model.addAttribute("resolutions", resolutions);

        Iterable<MemorySize> memorySizes = memoryRepository.findAll();
        model.addAttribute("memorySizes", memorySizes);

        Iterable<FlashMemorySize> flashMemorySizes = flashMemorySizeRepository.findAll();
        model.addAttribute("flashMemorySizes", flashMemorySizes);

        Iterable<BatteryCapacity> batteryCapacities = batteryCapacityRepository.findAll();
        model.addAttribute("batteryCapacities", batteryCapacities);

        Iterable<Color> colors = colorRepository.findAll();
        model.addAttribute("colors", colors);

        Iterable<Cpu> cpus = cpuRepository.findAll();
        model.addAttribute("cpus", cpus);

        Iterable<DisplayType> displayTypes = displayTypeRepository.findAll();
        model.addAttribute("displayTypes", displayTypes);

        Iterable<Manufacturer> manufacturers = manufacturerRepository.findAll();
        model.addAttribute("manufacturers", manufacturers);
    }
}
