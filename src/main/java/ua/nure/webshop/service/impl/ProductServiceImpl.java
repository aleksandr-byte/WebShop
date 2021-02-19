package ua.nure.webshop.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.nure.webshop.domain.Computer;
import ua.nure.webshop.domain.Product;
import ua.nure.webshop.domain.Smartphone;
import ua.nure.webshop.domain.Smartwatches;
import ua.nure.webshop.repos.ComputerRepository;
import ua.nure.webshop.repos.ProductRepository;
import ua.nure.webshop.repos.SmartphoneRepository;
import ua.nure.webshop.repos.SmartwatchesRepository;
import ua.nure.webshop.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("${images.folder.path}")
    private String folderPath;
    private final ProductRepository productRepository;
    private final SmartphoneRepository smartphoneRepository;
    private final ComputerRepository computerRepository;
    private final SmartwatchesRepository smartwatchesRepository;

    public ProductServiceImpl(ProductRepository productRepository, SmartphoneRepository smartphoneRepository, ComputerRepository computerRepository, SmartwatchesRepository smartwatchesRepository) {
        this.productRepository = productRepository;
        this.smartphoneRepository = smartphoneRepository;
        this.computerRepository = computerRepository;
        this.smartwatchesRepository = smartwatchesRepository;
    }

    @Override
    public Page<Product> findAllProducts(PageRequest pageRequest) {
        Page<Product> products = productRepository.findAll(pageRequest);
        setImageUrl(products.getContent());
        return products;
    }

    @Override
    public Page<Product> findProductsByCategoryName(PageRequest pageRequest, String categoryName) {
        Page<Product> products = productRepository.findAllByCategoryCategoryName(pageRequest, categoryName);
        setImageUrl(products.getContent());
        return products;
    }

    @Override
    public Page<Smartphone> findAllSmartphones(PageRequest pageRequest) {
        Page<Smartphone> smartphones = smartphoneRepository.findAll(pageRequest);
        setImageUrl((List<Product>)(List<?>) smartphones.getContent());
        return smartphones;
    }

    @Override
    public Page<Computer> findAllComputers(PageRequest pageRequest) {
        Page<Computer> computers = computerRepository.findAll(pageRequest);
        setImageUrl((List<Product>)(List<?>) computers.getContent());
        return computers;
    }

    @Override
    public Page<Smartwatches> findAllSmartwatches(PageRequest pageRequest) {
        Page<Smartwatches> smartwatches = smartwatchesRepository.findAll(pageRequest);
        setImageUrl((List<Product>)(List<?>) smartwatches.getContent());
        return smartwatches;
    }

    private void setImageUrl(List<Product> products) {
        products.forEach(computer -> {
            computer.setImageUrl(folderPath + computer.getImageName());
        });
    }
}
