package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	public Admin findByEmailId(String email);
	
}
