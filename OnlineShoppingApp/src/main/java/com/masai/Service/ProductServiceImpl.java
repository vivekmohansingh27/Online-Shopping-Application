package com.masai.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.ProductException;
import com.masai.Repository.CategoryRepository;
import com.masai.Repository.ProductRepository;
import com.masai.model.Product;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository proRepo;
	
	@Autowired
	private CategoryRepository catRepo;
	
	@Override
	public List<Product> viewAllProduct() throws ProductException {
		List<Product> productList = proRepo.findAll();
		if(productList.isEmpty()) {
			throw new ProductException("No product in the List");
		}
		return productList;
	}

	@Override
	public Product addProduct(Product product) throws ProductException {
		if(product==null) {
			throw new ProductException("No Product is passed");
		}
		 
		return proRepo.save(product);
	}

	@Override
	public Product updateProduct(Product product) throws ProductException {
		
		Optional<Product> prod = proRepo.findById(product.getProductId());
		if(prod.isEmpty()) {
			throw new ProductException("No Product is present in the List");
		}
		
		Product p = prod.get();
		p.setPrice(product.getPrice());
		p.setProductName(product.getProductName());
		
		p.setCategory(product.getCategory());
		p.setDescription(product.getDescription());
		p.setManufacturer(product.getManufacturer());
		p.setQuantity(product.getQuantity());
		
		return proRepo.save(p);
		
	}

	@Override
	public Product getProductById(Integer id) throws ProductException {
		
		Optional<Product> prod = proRepo.findById(id);
		if(prod.isEmpty()) {
			throw new ProductException("No Product is present in the List");
		}
		return prod.get();
	}
	
	@Override
	public Product deleteProductById(Integer id) throws ProductException {
		
		Optional<Product> prod = proRepo.findById(id);
		if(prod.isEmpty()) {
			throw new ProductException("No Product is present in the List");
		}
		Product p = prod.get();
		proRepo.deleteById(id);
		return p;
	}

//	@Override
//	public List<Product> getAllProductByCategoryName(String name) throws ProductException {
//		String catName = name.toUpperCase();
//		List<Product> proList = proRepo.getAllProductByCategoryName(catName);
//		if(proList.isEmpty()) {
//			throw new ProductException("No Product is present in the category");
//		}
//		
//		return proList;
//	}
	@Override
	public List<Product> getAllProductByCategoryName(String name) throws ProductException {
		String catName = name.toUpperCase();
		List<Product> proList = catRepo.findByCatName(catName);
		if(proList.isEmpty()) {
			throw new ProductException("No Product is present in the category");
		}
		
		return proList;
	}

	
}
