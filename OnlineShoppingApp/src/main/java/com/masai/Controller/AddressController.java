package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.CustomerException;
import com.masai.Service.AddressService;
import com.masai.model.Address;

@RestController
@CrossOrigin(origins = "*")
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	@GetMapping("/address/{aid}")
	public ResponseEntity<Address> getAddressByIdHanller(@PathVariable Integer aid) throws CustomerException {
		Address address = addressService.getAddress(aid);
		return new ResponseEntity<>(address,HttpStatus.OK);
	}
	
	@GetMapping("/address")
	public ResponseEntity<List<Address>> getAddressByIdHanller() throws CustomerException {
		List<Address> address = addressService.getAllAddress();
		return new ResponseEntity<>(address,HttpStatus.OK);
	}

}
