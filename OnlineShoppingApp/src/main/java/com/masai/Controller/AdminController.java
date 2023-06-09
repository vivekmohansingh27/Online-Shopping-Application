package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.AdminException;
import com.masai.Service.AdminService;
import com.masai.Service.CategoryService;
import com.masai.Service.ProductService;
import com.masai.Service.ProductServiceImpl;
import com.masai.model.Category;
import com.masai.model.Product;


@RestController
@CrossOrigin(origins = "*")
public class AdminController {
	
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private ProductService proService;
    
    @Autowired
    private CategoryService catService;
    
    @GetMapping("admin/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		
		return new ResponseEntity<>(proService.viewAllProduct(),HttpStatus.OK);
	}
    
    
    @GetMapping("admin/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id){
		
		return new ResponseEntity<>(proService.getProductById(id),HttpStatus.OK);
	}

    
    @PostMapping("admin/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product product ,@RequestParam("sessionKey") String key){
		
		return new ResponseEntity<>(proService.addProduct(product,key),HttpStatus.OK);
	}
    
    
    
    @PutMapping("admin/products")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product,@RequestParam("sessionKey") String key){
		
		return new ResponseEntity<>(proService.updateProduct(product,key),HttpStatus.OK);
	}
    
    @DeleteMapping("admin/products/{id}")
	public ResponseEntity<Product> deleteProductById(@PathVariable("id") Integer id, @RequestParam("sessionKey") String key){
		
		return new ResponseEntity<>(proService.deleteProductById(id ,key),HttpStatus.OK);
	}
    
    @GetMapping("admin/category")
	public ResponseEntity<List<Category>> getAllProductsHandler(){
		
		List<Category> list  = catService.getAllCategoty();
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
    
    
    @GetMapping("admin/category/name")
	public ResponseEntity<List<Product>> getAllProductsHandler(@RequestParam String category){
		
		List<Product> list  = catService.getAllProductsByCategory(category);
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
    
    @PostMapping("admin/category")
	public ResponseEntity<Category> addCategory(@RequestBody Category category, @RequestParam("sessionKey") String key){
		
		return new ResponseEntity<>(proService.addCategory(category ,key),HttpStatus.OK );
	}
	
	@DeleteMapping("admin/category/{id}")
	public ResponseEntity<Category> deleteCategoryById(@PathVariable("id") Integer id, @RequestParam("sessionKey") String key){
		
		return new ResponseEntity<>(proService.deleteCategory(id,key),HttpStatus.OK);
	}
}
