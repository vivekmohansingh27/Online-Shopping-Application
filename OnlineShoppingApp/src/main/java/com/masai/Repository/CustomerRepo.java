package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
	@Query("Select c.mobileNumber from Customer c where c.mobileNumber=:mobileNumber")
	public Customer findCustomerByMobileNumber(String mobileNumber) ;

}
