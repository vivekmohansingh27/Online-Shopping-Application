package com.masai.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.AdminException;
import com.masai.Exception.UserException;
import com.masai.Repository.AdminRepository;
import com.masai.Repository.CustomerRepo;
import com.masai.Repository.UserSession;
import com.masai.model.Admin;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.User;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private UserSession session;
	
	@Autowired 
	private AdminRepository adminRepo;

	@Override
	public String loginToAccount(User user) {
		if (user.getType().equalsIgnoreCase("customer")) {
			Customer customer = customerRepo.findByMobileNumber(user.getUserId());
			if (customer == null)
				throw new UserException("Mobile number not existed");

			Optional<CurrentUserSession> optional = session.findById(customer.getCustomerId());

			if (optional.isPresent())
				throw new UserException("Already Logged In");
			if (customer.getPassword().equalsIgnoreCase(user.getPassword())) {
				String key = RandomString.make(6);

				CurrentUserSession currentUserSession = new CurrentUserSession(customer.getCustomerId(), key,
						LocalDateTime.now());

				session.save(currentUserSession);

				return currentUserSession.toString();
			} else {
				throw new UserException("Please Provide valid password");
			}
		}else if (user.getType().equalsIgnoreCase("admin")) {
			Admin admin = adminRepo.findByEmailId(user.getUserId());
			
			if(admin == null) {
				throw new AdminException("Email id does not existed");
			}
			
			Optional<CurrentUserSession> optional= session.findById(admin.getId());
			
			if(optional.isPresent()) {
				throw new AdminException("Already Logged In");
			}
			
			if(admin.getPassword().equalsIgnoreCase(user.getPassword())) {
				String key= RandomString.make(6);
				
				CurrentUserSession currentUserSession= new CurrentUserSession(admin.getId(), key, LocalDateTime.now());
				
				session.save(currentUserSession);
				return currentUserSession.toString();
				
			}
		} else { 
			throw new UserException("Invalid User detailes ");
		}
		return "Something went wrong";
			

	}

	@Override
	public String logOutFromAccount(String key) {
		CurrentUserSession currentUserSession = session.findByUuid(key);
		if(currentUserSession.equals(null)) throw new UserException("User Not Logged In with this number");
		
		session.delete(currentUserSession);
		return "Logged Out Successfully !";
	}

}
