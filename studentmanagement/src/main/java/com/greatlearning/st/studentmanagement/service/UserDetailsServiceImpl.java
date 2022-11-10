package com.greatlearning.st.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greatlearning.st.studentmanagement.entity.Users;
import com.greatlearning.st.studentmanagement.repository.UserRepository;
import com.greatlearning.st.studentmanagement.security.MyUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Users user = userRepository.getUserByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid Username");
		}
		UserDetails userDetails = new MyUserDetails(user);
		return userDetails;
	}
}
