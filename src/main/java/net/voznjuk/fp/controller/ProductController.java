package net.voznjuk.fp.controller;

import net.voznjuk.fp.domain.Product;
import net.voznjuk.fp.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
@PreAuthorize("hasAuthority('ADMIN')")
public class ProductController {
    @Autowired
    private ProductRepo productRepo;

    @GetMapping
    public String productList(Model model){
        model.addAttribute("products", productRepo.findAll());

        return "products";
    }

    @PostMapping
    public String productAdd(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Long stock,
            @RequestParam float price, Model model
    ) {
        Product product = new Product(name, description, stock, price);
        productRepo.save(product);

        model.addAttribute("products", productRepo.findAll());

        return "products";
    }
}
