package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Cart;


@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {


}
