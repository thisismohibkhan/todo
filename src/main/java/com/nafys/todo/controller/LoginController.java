package com.nafys.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nafys.todo.service.AuthenticationService;

@Controller
@SessionAttributes("name")
public class LoginController {

	@Autowired
	private AuthenticationService authenticationService;
	
	@GetMapping("login")
	public String login(ModelMap model) {
		return "login";
	}
	
	@PostMapping("login")
	public String welcome(String name, String password, ModelMap model) {
		Boolean isAuthenticated = authenticationService.authenticate(name, password);
		if(isAuthenticated) {
			model.addAttribute("name", name);
			return "welcome";
		}
		model.addAttribute("invalidCreiential", "Invalid username or password.");
		return "login";
	}
}
