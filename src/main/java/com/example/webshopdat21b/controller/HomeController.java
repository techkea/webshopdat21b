package com.example.webshopdat21b.controller;

import com.example.webshopdat21b.model.Product;
import com.example.webshopdat21b.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    ProductRepository productRepository;

    public HomeController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("products", productRepository.getAll());
        return "index";
    }

    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name", "Frederik");
        model.addAttribute("class", "Dat21b");
        return "thymeleaf";
    }

    @GetMapping("/formtest")
    public String showFormTest(){

        return "formtest";
    }

    @PostMapping("/formtest")
    public String formtest(@RequestParam("name") String navn, @RequestParam("pwd") String kodeord, Model model){
        System.out.println(navn + ", " + kodeord);
        model.addAttribute("mitNavn", navn);
        model.addAttribute("hemmeligtKodeord", kodeord);

        return "redirect:/formtest";
    }

    @GetMapping("/create")
    public String showCreateProduct(){
        return "create";
    }

    @PostMapping("/create")
    public String createProduct(@RequestParam("name") String navn, @RequestParam("price") int pris){
        //lav nyt produkt
        Product newProduct = new Product();
        newProduct.setName(navn);
        newProduct.setPrice(pris);

        //gem nyt produkt
        productRepository.addProduct(newProduct);

        //vis liste af produkter
        return "redirect:/";

    }

}











