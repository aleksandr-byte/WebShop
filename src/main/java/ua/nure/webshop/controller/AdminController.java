package ua.nure.webshop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import ua.nure.webshop.domain.Products;
import ua.nure.webshop.service.ParametersService;
import ua.nure.webshop.service.ProductService;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private ProductService productService;
    private ParametersService parametersService;
    @Value("${images.path}")
    private String folderPath;
    @Value("${full.image.path}")
    private String fullFolderPath;
    public AdminController(ProductService productService, ParametersService parametersService) {
        this.productService = productService;
        this.parametersService = parametersService;
    }

    @GetMapping()
    public String index() {
        return "products/products";
    }

    @PostMapping("/change")
    public String choose(Model model,
                         @RequestParam(name = "productID")  Long productID,
                         @RequestParam(name = "file", required = false) MultipartFile file,
                         @RequestParam(name = "name", required = false) Optional<String> name,
                         @RequestParam(name = "price", required = false) Optional<String> price,
                         @RequestParam(name = "description", required = false) Optional<String> description,
                         @RequestParam(name = "diagonal", required = false) Optional<String> diagonalParam,
                         @RequestParam(name = "resolution", required = false) Optional<String> resolutionsParam,
                         @RequestParam(name = "memorySize", required = false) Optional<String> memorySizesParam,
                         @RequestParam(name = "flashMemorySize", required = false) Optional<String> flashMemorySizesParam,
                         @RequestParam(name = "batteryCapacity", required = false) Optional<String> batteryCapacitiesParam,
                         @RequestParam(name = "color", required = false) Optional<String> colorsParam,
                         @RequestParam(name = "cpu", required = false) Optional<String> cpusParam,
                         @RequestParam(name = "displayType", required = false) Optional<String> displayTypesParam,
                         @RequestParam(name = "manufacturer", required = false) Optional<String> manufacturerParam) throws IOException {

        Products product = productService.findProductByID(Long.valueOf(productID));

        String nameID = name.orElse(product.getName());
        String priceID = price.orElse(String.valueOf(product.getPrice()));
        String descriptionID = description.orElse(product.getDescription());
        product.setName(nameID);
        product.setPrice(BigDecimal.valueOf(Double.valueOf(priceID)));
        product.setDescription(descriptionID);

        String diagonalID = diagonalParam.orElse(String.valueOf(product.getDiagonal_id()));
        String resolutionID = resolutionsParam.orElse(String.valueOf(product.getResolution_id()));
        String memorySizeID = memorySizesParam.orElse(String.valueOf(product.getMemory_size_id()));
        String flashMemorySizeID = flashMemorySizesParam.orElse(String.valueOf(product.getFlash_memory_size_id()));
        String batteryCapacityID = batteryCapacitiesParam.orElse(String.valueOf(product.getBattery_capacity_id()));
        String colorID = colorsParam.orElse(String.valueOf(product.getColor_id()));
        String cpuID = cpusParam.orElse(String.valueOf(product.getCpu_id()));
        String displayTypeID = displayTypesParam.orElse(String.valueOf(product.getDisplay_type_id()));
        String manufacturerID = manufacturerParam.orElse(String.valueOf(product.getManufacturer_id()));
        product.setDiagonal_id(Long.valueOf(diagonalID));
        product.setResolution_id(Long.valueOf(resolutionID));
        product.setMemory_size_id(Long.valueOf(memorySizeID));
        product.setFlash_memory_size_id(Long.valueOf(flashMemorySizeID));
        product.setBattery_capacity_id(Long.valueOf(batteryCapacityID));
        product.setColor_id(Long.valueOf(colorID));
        product.setCpu_id(Long.valueOf(cpuID));
        product.setDisplay_type_id(Long.valueOf(displayTypeID));
        product.setManufacturer_id(Long.valueOf(manufacturerID));

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(folderPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            file.transferTo(new File(fullFolderPath + file.getOriginalFilename()));

            product.setImageUrl(folderPath + file.getOriginalFilename());
            product.setImageName(file.getOriginalFilename());
        }

        model.addAttribute("product", product);

        session().removeAttribute("product");
        model.addAttribute("product", product);
        session().setAttribute("product", product);
        parametersService.setParametersToModel(model);
        return "admin/admin";
    }

    @PostMapping("/update")
    public String update() {
        Products product = (Products) session().getAttribute("product");
        productService.updateProduct(product);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String add() {
        Products product = (Products) session().getAttribute("product");
        product.setId(Long.valueOf(0));
        productService.createProduct(product);
        return "redirect:/";
    }

    private HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true);
    }
}
