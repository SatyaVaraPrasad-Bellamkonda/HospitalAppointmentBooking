package com.hospital.hospital_validation.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;
	private boolean locked;


	public CustomUserDetails(String mail, String password2, String authority,boolean locked) {
		username=mail;
		password=password2;
		authorities = Arrays.stream(authority.split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		this.locked=locked;
	
	}

	public CustomUserDetails() {
		super();
	}

	public CustomUserDetails(String Patient_username, String patient_password) {
		username = Patient_username;
		password=patient_password;
		authorities=Collections.singletonList(new SimpleGrantedAuthority("patient"));
		this.locked=false;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	 @Override
	  public boolean isAccountNonLocked() {
	        return !locked;
	  }
	
	

}