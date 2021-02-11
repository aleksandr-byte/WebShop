package ua.nure.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.nure.webshop.domain.Categories;
import ua.nure.webshop.domain.Product;
import ua.nure.webshop.repos.ProductRepository;
import ua.nure.webshop.service.CategoriesService;
import ua.nure.webshop.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/")
public class ProductsController {

    @Value("${default.number.of.products.on.page}")
    private int defaultProductQuantity;
    private ProductService productService;
    private CategoriesService categoriesService;

    @Autowired
    public ProductsController(ProductService productService, CategoriesService categoriesService) {
        this.productService = productService;
        this.categoriesService = categoriesService;
    }

    @GetMapping()
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {
        Page<Product> productsPage = productService.findAllProducts(setPageRequest(page, size));
        model.addAttribute("productPage", productsPage);

        Iterable<Categories> categories = categoriesService.findAllCategories();
        model.addAttribute("categories", categories);

        setPageNumbersInModel(productsPage, model);

        return "products/products";
    }

    @GetMapping("/products/{categoryName}")
    public String findAllProductsByCategory(@PathVariable String categoryName,
                                            Model model,
                                            @RequestParam("page") Optional<Integer> page,
                                            @RequestParam("size") Optional<Integer> size,
                                            @RequestParam List<String> search) {



        Page<Product> productsPage = productService.findProductsByCategoryName(setPageRequest(page, size), categoryName);
        model.addAttribute("productPage", productsPage);

        List<Categories> categories = new ArrayList();
        categories.add(new Categories(categoryName));
        model.addAttribute("categories", categories.get(0));

        setPageNumbersInModel(productsPage, model);

        return "products/products";
    }

    private PageRequest setPageRequest(Optional<Integer> page, Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(defaultProductQuantity);
        return PageRequest.of(currentPage - 1, pageSize);
    }

    private Model setPageNumbersInModel(Page<Product> productsPage, Model model) {
        int totalPages = productsPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return model;
    }
}
