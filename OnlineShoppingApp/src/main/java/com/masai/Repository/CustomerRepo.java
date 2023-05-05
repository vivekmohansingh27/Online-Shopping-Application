package com.masai.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
	public Customer findByMobileNumber(String mobileNumber) ;
	
	@Query("select c from Customer c")
	public List<Customer> getAllCustmer();

}
