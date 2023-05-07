package com.masai.Service;

import com.masai.model.CurrentUserSession;
import com.masai.model.User;

public interface UserService {
	public CurrentUserSession loginToAccount(User user) ;
	public String logOutFromAccount(String key);

}
