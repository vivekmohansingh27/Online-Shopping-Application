package com.masai.Service;

import java.util.List;

import com.masai.model.Product;

public interface AdminService {
    public List<Product> getProductList();
    public Product getProductById(Long id);
    public void addProduct(Product product);
    public void updateProduct(Product product);
    public void removeProduct(Long id);
}
