package com.masai.Service;

import java.util.List;
import java.util.Optional;
import com.masai.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.AlreadyExistedException;
import com.masai.Exception.InputInvalidException;
import com.masai.Exception.UserException;
import com.masai.Repository.CartRepository;
import com.masai.Repository.CustomerRepo;
import com.masai.Repository.UserRepo;
import com.masai.Repository.UserSession;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private UserSession userRepo;

	@Override
	public Customer saveCustomer(Customer customer) {

//		Customer customer2 = customerRepo.findCustomerByMobileNumber(customer.getMobileNumber());

		Optional<Customer> customer2 = customerRepo.findById(customer.getCustomerId());
		if (customer2.isPresent())
			throw new AlreadyExistedException("Customer already exists ");


		
		
		Customer savedCustomer = customerRepo.save(customer);

		Cart cart = new Cart();
		cart.setCartId(savedCustomer.getCustomerId());
		cart.setCustomer(savedCustomer);

		cart.setProduct(null);

		cartRepo.save(cart);

		savedCustomer.setCart(cart);

		return customerRepo.save(savedCustomer);

	}

	@Override
	public Customer deleteCustomer(Integer id, String key) {
		
		CurrentUserSession user = userRepo.findByUuid(key);
		
		if(user==null) {
			throw new UserException("Please Login first");
		}

		Optional<Customer> customer2 = customerRepo.findById(id);

		if (customer2.isEmpty())
			throw new InputInvalidException("Wrong Customer Id");

		Customer customer3 = customerRepo.save(customer2.get());
		return customer3;
	}

	@Override
	public Customer updateCustomer(Customer customer, String key) {

		CurrentUserSession user = userRepo.findByUuid(key);
		
		if(user==null) {
			throw new UserException("Please Login first");
		}
		Optional<Customer> customer2 = customerRepo.findById(customer.getCustomerId());

		if (customer2.isEmpty())
			throw new InputInvalidException("Wrong Customer Id");
		
		customer2.get().setAddress(customer.getAddress());
			
		 
		return customerRepo.save(customer);
	}

	@Override
	public Customer getCustomerByID(Integer customerid) {
		Optional<Customer> customer2 = customerRepo.findById(customerid);
		if (customer2.isEmpty())
			throw new InputInvalidException("Wrong Customer Id");
		return customer2.get();
	}

	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> customers = customerRepo.getAllCustmer();
		return customers;
	}

	@Override
	public Customer saveAddress(Address address, Customer customer) {

//		Customer customer2 = customerRepo.findCustomerByMobileNumber(customer.getMobileNumber());

		Optional<Customer> customer2 = customerRepo.findById(customer.getCustomerId());
		if (customer2.isEmpty())
			throw new AlreadyExistedException("Customer not exists");
		Customer customer3 = customer2.get();
		customer3.setAddress(address);

		return customerRepo.save(customer3);
	}
	@Override
	public Customer removeAddress(Customer customer) {

//		Customer customer2 = customerRepo.findCustomerByMobileNumber(customer.getMobileNumber());

		Optional<Customer> customer2 = customerRepo.findById(customer.getCustomerId());
		if (customer2.isEmpty())
			throw new AlreadyExistedException("Customer not exists");
		Customer customer3 = customer2.get();
		customer3.setAddress(null);

		return customerRepo.save(customer3);
	}

}
