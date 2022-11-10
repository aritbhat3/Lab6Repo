package com.greatlearning.st.studentmanagement.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.management.relation.Role;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.greatlearning.st.studentmanagement.entity.Users;
import com.greatlearning.st.studentmanagement.entity.Roles;

public class MyUserDetails implements UserDetails{
	
	private Users user;

	public MyUserDetails(Users user) {
		this.user = user;
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		for(Roles role : user.getRoles()) {
			SimpleGrantedAuthority auth = new SimpleGrantedAuthority(role.getName());
		}
		return roles;
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override 
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
