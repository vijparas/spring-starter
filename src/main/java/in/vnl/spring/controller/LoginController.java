package in.vnl.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import in.vnl.spring.config.WebSecurityConfig;

@Controller
public class LoginController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManagerBuilder auth;
	
	@Autowired
	private WebSecurityConfig webSecurityConfig;
	
	@GetMapping("/login")
	public String showLoginForm() {
		return "auth/login";
	}
	
//	@PostMapping("/login")
//	public String login() {
//		System.out.println("It is coming here");
//		try {
//			
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
//		return "auth/login";
//	
//	}
	
}
