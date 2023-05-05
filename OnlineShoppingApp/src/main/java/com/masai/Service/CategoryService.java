package com.masai.Service;

import java.util.List;

import com.masai.model.Category;
import com.masai.model.CategoryEnum;
import com.masai.model.Product;

public interface CategoryService {

	

	List<Product> getAllProductsByCategory(String catName);
}
