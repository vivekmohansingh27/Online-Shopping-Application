package com.masai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class User {

	@Id
	private String phoneNo;
	private String password;
	private String type;
	
	
}
