package com.masai.Service;

import com.masai.model.User;

public interface UserService {
	public String loginToAccount(User user) ;
	public String logOutFromAccount(String key);

}
