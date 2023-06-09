package com.masai.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer>{

	@Query("select a from Address a")
	public List<Address> getAllAddress() ;
}
