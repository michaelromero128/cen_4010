package com.example.demo.controller;

import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value= {"/","/login"}, method =RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView= new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
		
	}
	
	@RequestMapping(value="/registration", method =RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView= new ModelAndView();
		User userDTO = userService.findUserByUserName(user.getUserName());
		if(userDTO != null) {
			bindingResult.rejectValue("userName",  "error.user", "There is a already a suer registered with the user name provided");
		}
		
		if(user.getUserName().contains("'") || user.getUserName().contains("<") || user.getUserName().contains(">")) {
			bindingResult.rejectValue("userName", "error.user", "Illegal Character in the user name. Do not use >,<, or '");
		}
		if(bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		}else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" );
	    modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
	    modelAndView.setViewName("home");
	    return modelAndView;
	}
	
	
	
	
}
