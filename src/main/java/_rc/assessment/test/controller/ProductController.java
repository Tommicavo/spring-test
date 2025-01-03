package _rc.assessment.test.controller;

import _rc.assessment.test.model.Product;
import _rc.assessment.test.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model) {
        return listProducts(model);
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        double totalValue = 0.0;
        for (Product product : products) {
            double value = product.getProductValue();
            totalValue += value;
        }
        model.addAttribute("totalValue", totalValue);
        return "products/list";
    }

    @GetMapping("/products/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("create", true);
        return "products/productForm";
    }

    @PostMapping("/products")
    public String saveProduct(Model model, @Valid @ModelAttribute Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("create", true);
            return "products/productForm";
        }
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        productService.getProductById(id).ifPresent(product -> model.addAttribute("product", product));
        model.addAttribute("create", false);
        return "products/productForm";
    }

    @PostMapping("/products/{id}")
    public String updateProduct(Model model, @Valid @ModelAttribute Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("create", false);
            return "products/productForm";
        }
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Model model) {
        productService.deleteProduct(id);
        return listProducts(model);
    }
}
