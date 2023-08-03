package com.greatlearning.CollegeFest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greatlearning.CollegeFest.Entity.User;
import com.greatlearning.CollegeFest.repository.UserRepository;
import com.greatlearning.CollegeFest.security.MyUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userRepo.getUserByUsername(username);	
		
		if(user==null)
			throw new UsernameNotFoundException("could not find user");
		return new MyUserDetails(user);
	}

}
