package com.masai.Service;

import com.masai.model.Customer;

public interface CustomerService {
	public Customer saveCustomer(Customer customer);

	public Customer deleteCustomer(Integer id);

	public Customer updateCustomer(Customer customer);

	public Customer getCustomerByID(Integer customerid);
}
