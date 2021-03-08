package ua.nure.webshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ua.nure.webshop.domain.*;

public interface ProductService {

    Page<Products> findAllProducts(PageRequest pageRequest);

    Page<Products> findProductsByCategoryName(PageRequest pageRequest, String categoryName);

    Page<Smartphone> findAllSmartphones(PageRequest pageRequest);

    Page<Computer> findAllComputers(PageRequest pageRequest);

    Page<Smartwatch> findAllSmartwatches(PageRequest pageRequest);

/*    Page<Smartphone> findSmartphonesByCondition(Parameters parameters,
                                                       PageRequest pageRequest);

    Page<Computer> findComputersByCondition(Parameters parameters,
                                            PageRequest pageRequest);

    Page<Smartwatch> findSmartwatchesByCondition(Parameters parameters,
                                                 PageRequest pageRequest);*/

    Page<Products> findProductsByCategoryAndCondition(Parameters parameters,
                                                      String categoryName,
                                                      PageRequest pageRequest);
}
