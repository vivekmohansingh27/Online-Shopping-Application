package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.masai.Exception.AdminException;
import com.masai.Service.AdminService;
import com.masai.model.Product;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
    @Autowired
    private AdminService adminService;
    
    @GetMapping("/products")
    public String viewProductList(Model model) throws AdminException {
        List<Product> productList = adminService.getProductList();
        model.addAttribute("productList", productList);
        return "product-list";
    }
    
    
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) throws AdminException{
    	Product product = adminService.getProductById(id);
    	
    	return new ResponseEntity<>(product, HttpStatus.FOUND);
    }

    
    @GetMapping("/add-product")
    public String showAddProductForm(Model model) throws AdminException{
        model.addAttribute("product", new Product());
        return "add-product";
    }
    
    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("product") Product product) throws AdminException{
        adminService.addProduct(product);
        return "redirect:/admin/products";
    }
    
    @GetMapping("/edit-product/{id}")
    public String showEditProductForm(@PathVariable("id") Integer id, Model model) throws AdminException{
        Product product = adminService.getProductById(id);
        model.addAttribute("product", product);
        return "edit-product";
    }
    
    @PostMapping("/edit-product")
    public String editProduct(@ModelAttribute("product") Product product)throws AdminException {
        adminService.updateProduct(product);
        return "redirect:/admin/products";
    }
    
    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) throws AdminException {
        adminService.removeProduct(id);
        return "redirect:/admin/products";
    }
}
