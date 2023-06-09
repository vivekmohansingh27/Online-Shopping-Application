package com.masai.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.ProductException;
import com.masai.Exception.UserException;
import com.masai.Repository.CategoryRepository;
import com.masai.Repository.ProductRepository;
import com.masai.Repository.UserSession;
import com.masai.model.Category;
import com.masai.model.CurrentUserSession;
import com.masai.model.Product;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository proRepo;
	
	@Autowired
	private CategoryRepository catRepo;
	
	@Autowired
	private UserSession userRepo;
	
	
	
	@Override
	public List<Product> viewAllProduct() throws ProductException {
		List<Product> productList = proRepo.findAll();
		if(productList.isEmpty()) {
			throw new ProductException("No product in the List");
		}
		return productList;
	}
	
	
	
	

	@Override
	public Product addProduct(Product product, String key) throws ProductException {
		
		

		CurrentUserSession user = userRepo.findByUuid(key);
		
		if(user==null) {
			throw new UserException("Please Login first");
		}
		
		
		Optional<Product> prod = proRepo.findById(product.getProductId());
		
		if(prod.isPresent()) {
			throw new ProductException(" Product is Already there");
		}
		
		 
		return proRepo.save(product);
	}
	
	
	
	

	@Override
	public Product updateProduct(Product product, String key) throws ProductException {
		
		
		
     CurrentUserSession user = userRepo.findByUuid(key);
		
		if(user==null) {
			throw new UserException("Please Login first");
		}
		
		
		
		Optional<Product> prod = proRepo.findById(product.getProductId());
		if(prod.isPresent()==false) {
			throw new ProductException("No Product is present in the List");
		}
		
		Product p = prod.get();
		p.setPrice(product.getPrice());
		p.setProductName(product.getProductName());
		Category existingCategory = p.getCategory();
		Category newCategory = product.getCategory();
		if (newCategory != null) {
		existingCategory.setCatName(newCategory.getCatName());
		existingCategory.setProduct(newCategory.getProduct());
		}
	//	p.setCategory(product.getCategory());
		
		p.setDescription(product.getDescription());
		p.setManufacturer(product.getManufacturer());
		p.setQuantity(product.getQuantity());
		
		return proRepo.save(p);
		
	}
	
	
//public Product updateProduct(Product product) throws ProductException {
//		
//		Optional<Product> optionalProduct = proRepo.findById(product.getProductId());
//		if(optionalProduct.isEmpty()) {
//			throw new ProductException("No Product is present in the List");
//		}
//		
//		Product existingProduct = optionalProduct.get();
//		existingProduct.setPrice(product.getPrice());
//		existingProduct.setProductName(product.getProductName());
//		existingProduct.setQuantity(product.getQuantity());
//		existingProduct.setDescription(product.getDescription());
//		
//		Category existingCategory = existingProduct.getCategory();
//		Category newCategory = product.getCategory();
//		if (newCategory != null) {
//			existingCategory.setCategoryName(newCategory.getCategoryName());
//			existingCategory.setProducts(newCategory.getProducts());
//		}
//		
//		Manufacturer existingManufacturer = existingProduct.getManufacturer();
//		Manufacturer newManufacturer = product.getManufacturer();
//		if (newManufacturer != null) {
//			existingManufacturer.setName(newManufacturer.getName());
//			existingManufacturer.setAddress(newManufacturer.getAddress());
//			existingManufacturer.setProducts(newManufacturer.getProducts());
//		}
//		
//		return proRepo.save(existingProduct);
//		
//	}
	
	

	@Override
	public Product getProductById(Integer id) throws ProductException {
		
		Optional<Product> prod = proRepo.findById(id);
		if(prod.isPresent()==false) {
			throw new ProductException("No Product is present in the List");
		}
		return prod.get();
	}
	
	
	
	
	@Override
	public Product deleteProductById(Integer id, String key) throws ProductException, UserException {
	    // Check if user is authenticated
	    CurrentUserSession user = userRepo.findByUuid(key);
	    if (user == null) {
	        throw new UserException("Please log in first.");
	    }
	    
	    // Check if product exists
	    Optional<Product> optionalProduct = proRepo.findById(id);
	    if (!optionalProduct.isPresent()) {
	        throw new ProductException("Product not found.");
	    }
	    
	    // Delete the product and return it
	    Product product = optionalProduct.get();
	    proRepo.deleteById(id);
	    return product;
	}
//	public Product deleteProductById(Integer id, String Key) throws ProductException {
//		
//		
//		  CurrentUserSession user = userRepo.findByUuid(Key);
//			
//			if(user==null) {
//				throw new UserException("Please Login first");
//			}
//			
//		
//		Optional<Product> prod = proRepo.findById(id);
//		if(prod.isPresent()==false) {
//			throw new ProductException("No Product is present in the List");
//		}
//		Product p = prod.get();
//		proRepo.deleteById(id);
//		return p;
//	}



	
	
	@Override
	public Category addCategory(Category category, String Key) throws ProductException {
		
		  CurrentUserSession user = userRepo.findByUuid(Key);
			
			if(user==null) {
				throw new UserException("Please Login first");
			}
			
		
		
		Optional<Category> cat = catRepo.findById(category.getCatId());
		
		if(cat.isPresent()) {
			throw new ProductException("category is Already present");
		}
		 
		return catRepo.save(category);
	}
	
	
	
	
	
	@Override
	public Category deleteCategory(Integer catId , String Key) throws ProductException {
		
		
		  CurrentUserSession user = userRepo.findByUuid(Key);
			
			if(user==null) {
				throw new UserException("Please Login first");
			}
			
			
		
		Optional<Category> cat = catRepo.findById(catId);
		
		if(cat.isPresent()==false) {
			throw new ProductException("category is not present");
		}
		catRepo.deleteById(catId);
		return cat.get();
	}

	
}
