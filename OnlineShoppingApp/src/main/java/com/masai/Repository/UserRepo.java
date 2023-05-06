package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.CurrentUserSession;
import com.masai.model.User;

public interface UserRepo extends JpaRepository<User, String>{

	

}
