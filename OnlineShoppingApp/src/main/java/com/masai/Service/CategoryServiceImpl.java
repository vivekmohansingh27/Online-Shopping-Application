package com.masai.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Repository.CategoryRepository;
import com.masai.model.Category;
import com.masai.model.CategoryEnum;
import com.masai.model.Product;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository catrepo;

	@Override
	public List<Product> getAllProductsByCategory(String catName) {
		
		Integer n =null;
		List<Category> list = catrepo.findAll();
		for(Category ls: list) {
			if(ls.getCatName().toString().equals(catName)) {
				n = ls.getCatId();
			}
		}
		Optional<Category> cat = catrepo.findById(n);
		 
 		return cat.get().getProduct();
	}
	
	
	
	@Override
	public List<Category> getAllCategoty(){
		return catrepo.findAll();
	}
	

	
	

}
