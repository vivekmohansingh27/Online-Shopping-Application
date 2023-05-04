package com.masai.Service;

import java.util.List;

import com.masai.Exception.ProductException;
import com.masai.model.Product;

public interface ProductService {

	public List<Product> viewAllProduct()throws ProductException;
	
	public Product addProduct(Product product)throws ProductException;
	
	public Product updateProduct(Product product)throws ProductException;
	
	public Product getProductById(Integer id)throws ProductException;
	
	public Product deleteProductById(Integer id) throws ProductException;

	public List<Product> getAllProductByCategoryName(String name)throws ProductException;
}
