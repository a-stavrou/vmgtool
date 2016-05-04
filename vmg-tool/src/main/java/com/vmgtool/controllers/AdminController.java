package com.vmgtool.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vmgtool.entity.Question;
import com.vmgtool.entity.User;
import com.vmgtool.services.UserService;

@Controller
@RequestMapping("/users")
public class AdminController {
	
	@Autowired
	private UserService userService;	
	
    //create a user.
	@ModelAttribute("user")
	public User constructUser() {
		return new User();
	}
	
	//find users
	@RequestMapping
	public String users(Model model){
		model.addAttribute("users", userService.findAll());
		return "users";
	}
    //code to remove a user with specific id
	@RequestMapping("/remove/{id}")
	public String removeUser(@PathVariable int id){
		userService.delete(id);
		return "redirect:/users.html";
	}
	//find details of a particular user.
	@RequestMapping("/{id}")
	public String detail (Model model,@PathVariable int id){
		model.addAttribute("user",userService.findOne(id));
		return "user-detail";
	}
	
	//edit user.
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(Model model,@Valid @ModelAttribute("user") User user){
		User dbUser = userService.findOne(user.getId());
		user.setPassword(dbUser.getPassword());
		userService.save(user);
		return "redirect:/pages/users.html";
	}
}
