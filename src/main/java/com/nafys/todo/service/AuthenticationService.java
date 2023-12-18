package com.nafys.todo.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

	public Boolean authenticate(String name, String password) {
		Boolean isUserAuthenticated = "mohib".equalsIgnoreCase(name);
		Boolean isPasswordAuthenticated = "khan".equals(password);
		return isUserAuthenticated && isPasswordAuthenticated;
	}
}
