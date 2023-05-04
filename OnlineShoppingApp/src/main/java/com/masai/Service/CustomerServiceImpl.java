package com.masai.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.AlreadyExistedException;
import com.masai.Exception.InputInvalidException;
import com.masai.Repository.CustomerRepo;
import com.masai.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepo customerRepo;

	@Override
	public Customer saveCustomer(Customer customer) {

		Customer customer2 = customerRepo.findCustomerByMobileNumber(customer.getMobileNumber());

		if (customer2 != null)
			throw new AlreadyExistedException("Customer existed with this mobile no ");

		customer2 = customerRepo.save(customer);
		return customer2;
	}

	@Override
	public Customer deleteCustomer(Integer id) {

		Optional<Customer> customer2 = customerRepo.findById(id);

		if (customer2 == null)
			throw new InputInvalidException("Wrong Customer Id");

		Customer customer3 = customerRepo.save(customer2.get());
		return customer3;
	}

	@Override
	public Customer updateCustomer(Customer customer) {

		Optional<Customer> customer2 = customerRepo.findById(customer.getCustomerId());

		if (customer2 == null)
			throw new InputInvalidException("Wrong Customer Id");

		Customer customer3 = customerRepo.save(customer2.get());
		return customer3;
	}

}
