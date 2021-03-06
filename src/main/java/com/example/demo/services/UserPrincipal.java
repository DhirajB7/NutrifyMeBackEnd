package com.example.demo.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;


public class UserPrincipal implements UserDetails{
	
	private User user;

	public UserPrincipal(User user) {

		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		this.user.getRoleList().forEach(role->{
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role); //ROLE_USER or ROLE_ADMIN is the must use formate
			authorities.add(authority);
		});
		return authorities;
	}

	@Override
	public String getUsername() {

		return this.user.getUsername();
	}

	@Override
	public String getPassword() {

		return this.user.getPassword();
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getRole(){
		return this.user.getRole();
	}

	public boolean getStatus(){
		return this.user.isUserStatus();
	}

	public String getHistory(){
		return user.getHistory();
	}

	public String  getCal(){
		return String.valueOf(user.getCaloriesPerDay());
	}



}
