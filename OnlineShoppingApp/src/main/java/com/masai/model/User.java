package com.masai.model;

import jakarta.persistence.Id;

public class User {

	@Id
	private String userId;
	private String password;
	private String type;
}
