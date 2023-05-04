package com.masai.Service;

import com.masai.Exception.CartException;
import com.masai.model.Cart;


public interface CartService {
	
	
	public Cart addProductToCart(Integer cartId, Integer productId, Integer quantity)throws CartException;
	
	public Cart removeProductFromCart(Integer cartId, Integer productId)throws CartException;
	
	public Cart updateProductQantity(Integer cartId, Integer productId, Integer quantity)throws CartException;
	
	public Cart removeAllProducts(Integer cartId)throws CartException ;
	
	public Cart viewAllProducts(Integer cartId)throws CartException ;
	
	

}
