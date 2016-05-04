package com.vmgtool.controllers;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vmgtool.entity.Role;
import com.vmgtool.entity.User;
import com.vmgtool.services.UserService;


@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public User constructUser() {
		return new User();
	}
	
	@RequestMapping
	public String showRegistrer() {
		return "user-registration";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doRegistrer(@Valid @ModelAttribute("user") User user, BindingResult result) {
		if(result.hasErrors()){
			return "user-registration";
		}
		Role role = new Role();
		role.setId(1);
		role.setName("ROLE_ADMIN");
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		
		user.setRoles(roles);
		userService.save(user);
		return "redirect:/register.html?success=true";
	}

}
