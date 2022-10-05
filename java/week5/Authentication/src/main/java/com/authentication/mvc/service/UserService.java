package com.authentication.mvc.service;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.authentication.mvc.models.LoginUser;
import com.authentication.mvc.models.User;
import com.authentication.mvc.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User find(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User registration(User use,BindingResult result) {
		User emailCheck = userRepository.findByEmail(use.getEmail()).orElse(null);
		if (emailCheck!= null) {
			result.rejectValue("email","key","email not available");
		}
		if (!use.getPassword().equals(use.getConfirm())){
			result.rejectValue("confirm","matches","Confirm passqord must mach password");
			
		}
		if (result.hasErrors()) {
			return null;
			
		}
		
		String hash = BCrypt.hashpw(use.getPassword(), BCrypt.gensalt());
		use.setPassword(hash);
		
		return userRepository.save(use);
	}
	 public User getOneByEmail(LoginUser loginUser,BindingResult result) {
		 Optional<User> user = userRepository.findByEmail(loginUser.getEmail());
		 if (user.isPresent()) {
			 if(BCrypt.checkpw(loginUser.getPassword(), user.get().getPassword())) {
				 return user.get();
			 }else {
				 result.rejectValue("email", "key", "Inavild Username/Password");
				 return null;
			 }
			 
		 }else {
			 result.rejectValue("email", "key", "Inavild Username/Password");
			 return null;
		 }
		 
	 }
	
}
