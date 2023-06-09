package com.masai.Service;

import java.util.List;

import com.masai.Exception.CustomerException;
import com.masai.model.Address;


/**
 * The AddressService interface provides methods for managing addresses.
 */
public interface AddressService {
	
	  
    /**
     * Retrieves a list of all addresses.
     *
     * @return A list of Address objects.
     */
	public List<Address> getAllAddress();
	
	/**
     * Retrieves an address by its ID.
     *
     * @param aid The ID of the address to retrieve.
     * @return The Address object corresponding to the ID.
     * @throws CustomerException if the address with the specified ID is not found.
     */
	public Address getAddress(Integer aid) throws CustomerException;
}
