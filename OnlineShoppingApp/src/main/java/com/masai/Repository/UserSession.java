package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.CurrentUserSession;

public interface UserSession extends JpaRepository<CurrentUserSession, Integer>{
	public  CurrentUserSession  findByUuid(String uuid);
	
}
