package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Service.CategoryService;
import com.masai.model.Category;
import com.masai.model.CategoryEnum;
import com.masai.model.Product;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService cs;
	
	@GetMapping("/category/name")
	public ResponseEntity<List<Product>> getAllProductsHandler(@RequestParam String category){
		
		List<Product> list  = cs.getAllProductsByCategory(category);
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	@GetMapping("/category")
	public ResponseEntity<List<Category>> getAllProductsHandler(){
		
		List<Category> list  = cs.getAllCategoty();
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
}
