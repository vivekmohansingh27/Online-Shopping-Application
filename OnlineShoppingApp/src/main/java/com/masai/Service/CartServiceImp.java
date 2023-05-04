package com.masai.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.CartException;
import com.masai.Repository.CartRepository;
import com.masai.Repository.ProductRepository;
import com.masai.model.Cart;
import com.masai.model.Product;

@Service
public class CartServiceImp implements CartService {
	
	
	@Autowired
	public CartRepository cRepo;
	
	
	@Autowired
	public ProductRepository pRepo;
	
	
	
	

	@Override
	public Cart addProductToCart(Integer cartId, Integer productId, Integer quantity) throws CartException {
		
		Optional<Cart> opCart= cRepo.findById(cartId);
		
		if(opCart.isEmpty()) {
			 throw new CartException ("Cart does not exists");
		}
		
		Cart cart=opCart.get();
		
		
		Optional<Product> opProduct= pRepo.findById(productId);
		
		if(opProduct.isPresent()) {
			 throw new CartException ("Product with productId "+productId+" is already added in the cart !");
		}
		
		
		Product p=opProduct.get();
		
		p.setQuantity(quantity);
		
		p.setCart(cart);
		
		cart.getProduct().add(p);
		
		cart.getCustomer().setCart(cart);
		
		return  cRepo.save(cart);
		
		
		
		
		
	}

	
	
	
	
	@Override
	public Cart removeProductFromCart(Integer cartId, Integer productId) throws CartException {
		
		Optional<Cart> opCart= cRepo.findById(cartId);
		
	    Cart cart=opCart.get();
		
		List<Product> productList=cart.getProduct();
		
		
		for(Product pro : productList) {
			
			if(pro.getProductId()==productId) {
				
				productList.remove(pro);
			}
		}
		
		
		cart.setProduct(productList);
		
		return  cart;
		
		
		
	}
	
	
	
	
	

	@Override
	public Cart updateProductQantity(Integer cartId, Integer productId, Integer quantity) throws CartException {
		
	Optional<Cart> opCart= cRepo.findById(cartId);
		
		if(opCart.isEmpty()) {
			 throw new CartException ("Cart is Empty");
		}
		
		
         Cart  cart=opCart.get();
		
		Optional<Product> opProduct= pRepo.findById(productId);
		
		if(opProduct.isEmpty()) {
			 throw new CartException ("Product with productId "+productId+" does not exists in the cart !");
		}
		
		
		
		List<Product> productList=cart.getProduct();
		
        for(Product pro : productList) {
			
			if(pro.getProductId()==productId) {
				
				pro.setQuantity(quantity);
			}
		}
		
		cart.setProduct(productList);
		
		return  cRepo.save(cart);
		
	}
	
	
	
	
	
	

	@Override
	public Cart removeAllProducts(Integer cartId) throws CartException {
		
		Optional<Cart> opCart= cRepo.findById(cartId);
		if(opCart.isEmpty()) {
			 throw new CartException ("Cart is Empty");
		}
		
		
        Cart cart=opCart.get();
        
        List<Product> productList=cart.getProduct();
        
        productList=new ArrayList<>();
        
        cart.setProduct(productList);
        
        return  cRepo.save(cart);
		
        
		
	}
	
	
	

	@Override
	public Cart viewAllProducts(Integer cartId) throws CartException  {
		
		
		Optional<Cart> opCart= cRepo.findById(cartId);
		if(opCart.isEmpty()) {
			 throw new CartException ("Cart is Empty");
		}
		
		
       Cart cart=opCart.get();
        
       return cart;
        
		
	}

}
