package com.masai.Service;

import java.util.List;

import com.masai.model.Address;
import com.masai.model.Customer;

public interface CustomerService {
	public Customer saveCustomer(Customer customer);

	public Customer deleteCustomer(Integer id,String key);

	public Customer updateCustomer(Customer customer, String key);

	public Customer getCustomerByID(Integer customerid);

	public List<Customer> getAllCustomer();

	public Customer saveAddress(Address address, Customer customer);
	
	public Customer removeAddress(Customer customer);
}
