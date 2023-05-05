package com.masai.Service;

import java.util.List;

import com.masai.Exception.CustomerException;
import com.masai.model.Address;

public interface AddressService {
	public List<Address> getAllAddress();
	public Address getAddress(Integer aid) throws CustomerException;
}
