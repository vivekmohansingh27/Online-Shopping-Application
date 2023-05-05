package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.masai.Exception.AdminException;
import com.masai.Service.AdminService;
import com.masai.Service.ProductService;
import com.masai.Service.ProductServiceImpl;
import com.masai.model.Category;
import com.masai.model.Product;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private ProductService proService;
    
    @GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		
		return new ResponseEntity<>(proService.viewAllProduct(),HttpStatus.OK);
	}
    
    
    @GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id){
		
		return new ResponseEntity<>(proService.getProductById(id),HttpStatus.OK);
	}

    
    @PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product product){
		
		return new ResponseEntity<>(proService.addProduct(product),HttpStatus.OK);
	}
    
    
    
    @PutMapping("/products")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		
		return new ResponseEntity<>(proService.updateProduct(product),HttpStatus.OK);
	}
    
    @DeleteMapping("/products/{id}")
	public ResponseEntity<Product> deleteProductById(@PathVariable("id") Integer id){
		
		return new ResponseEntity<>(proService.deleteProductById(id),HttpStatus.OK);
	}
    
    @PostMapping("/category")
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		
		return new ResponseEntity<>(proService.addCategory(category),HttpStatus.OK);
	}
	
	@DeleteMapping("/category/{id}")
	public ResponseEntity<Category> deleteCategoryById(@PathVariable("id") Integer id){
		
		return new ResponseEntity<>(proService.deleteCategory(id),HttpStatus.OK);
	}
}
