package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Service.CustomerService;
import com.masai.model.Address;
import com.masai.model.Customer;

import jakarta.validation.Valid;


@RestController
@CrossOrigin("*")
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@PostMapping("/customerSave")
	public ResponseEntity<Customer> saveCustmerHandller(@Valid @RequestBody Customer customer) {
		Address address = customer.getAddress();
		Customer customer3 = customer;
		customer3.setAddress(address);
		Customer customer2 = customerService.saveCustomer(customer3);
		return new ResponseEntity<>(customer2, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/customerDelete")
	public ResponseEntity<Customer> deleteCustmerHandller(@Valid @RequestBody Customer customer,@RequestParam("Session Key") String key) {
		Customer customer2 = customerService.deleteCustomer(customer.getCustomerId(),key);
		return new ResponseEntity<>(customer2, HttpStatus.OK);
	}

	
	@PutMapping("/customerUpdate")
	public ResponseEntity<Customer> updateCustmerHandller(@Valid @RequestBody Customer customer,@RequestParam("Session Key") String key) {
		Customer customer2 = customerService.updateCustomer(customer,key);
		return new ResponseEntity<>(customer2, HttpStatus.OK);
	}
	

	@GetMapping("/customerGet/{id}")
	public ResponseEntity<Customer> getCustmerByIdHandller(@Valid @PathVariable Integer id) {
		Customer customer2 = customerService.getCustomerByID(id);
		return new ResponseEntity<>(customer2, HttpStatus.OK);
	}
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> viewAllCustomersHandller() {
		List<Customer> customers = customerService.getAllCustomer();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

}
