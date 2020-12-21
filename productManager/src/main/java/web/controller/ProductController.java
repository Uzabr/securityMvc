package web.controller;

import org.hibernate.engine.transaction.internal.SynchronizationRegistryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Product;
import web.services.ProductService;

import javax.persistence.PostUpdate;

@Controller
public class ProductController {


    private ProductService productService;

    @Autowired
    public void setProductService (ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/")
    public String listProduct(ModelMap model) {
        model.addAttribute("product", new Product());
        model.addAttribute("listProducts", productService.listProducts());

        return "products";
    }
    @PostMapping(value = "/add")
    public String addProduct(@ModelAttribute("product") Product product) {
            productService.addProduct(product);
            return "redirect:/";
        }


    @GetMapping("/new")
    public String newProduct(ModelMap map) {
        Product product = new Product();
        map.addAttribute("product", product);
        return "productData";
    }


    @GetMapping("/remove/{id}")
    public String removeProduct(@PathVariable("id") int id){
        productService.removeProduct(id);

        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") Product product){
        productService.updateProduct(product);

        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String showUpdate(  ModelMap map, @PathVariable("id") long id) {

        map.addAttribute("product", productService.getProductById(id));

        return "edit_product";
    }

//    @GetMapping("/edit/{id}")
//    public String showUpdateForm(@PathVariable("id") long id, ModelMap map) {
//        Product product = productService.getProductById(id);
//        map.addAttribute("product", product);
//        return "edit_product";
//    }

//
//    @PostMapping("/update/{id}")
//    public String updateProduct(@PathVariable(value = "id") long id,
//                                Product product, ModelMap map) {
//                product = productService.getProductById(id);
//            productService.updateProduct(product);
//            map.addAttribute("product", product);
//            return "redirect:/";
//    }
//
//
//    @GetMapping("/edit/{id}")
//    public String productData(@PathVariable("id") int id, ModelMap modelMap){
//        modelMap.addAttribute("product", productService.getProductById(id));
//
//        return "edit_product";
//    }

}
