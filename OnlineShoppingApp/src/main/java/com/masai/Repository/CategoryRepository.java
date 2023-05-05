package com.masai.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Category;
import com.masai.model.CategoryEnum;
import com.masai.model.Product;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	public List<Category> findByCatName(CategoryEnum catName);


	
}
