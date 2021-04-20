package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.City;
import com.example.demo.model.Comment;
import com.example.demo.model.CommentDTO;
import com.example.demo.model.Text;
import com.example.demo.model.User;
import com.example.demo.service.CityService;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin
public class EndpointController {
	
	@Autowired
	CityService cityService;
	
	@Autowired
	UserService userService;
	@CrossOrigin
	@GetMapping(path= "/getComment/{cityName}")
	public List<CommentDTO> getComments(@PathVariable  String cityName){
		
		
		return cityService.getCommentsDTOFromCity(cityName);
	}
	@CrossOrigin(origins = "localhost")
	@PostMapping(path = "/postComment/{cityName}")
	public String postComment(@PathVariable String cityName ,@RequestBody Text text) {
		System.out.println(cityName);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
		String name = auth.getName();
		User user = userService.findUserByUserName(name);
		System.out.println(user.getId());
		Comment comment = new Comment();
		comment.setText(text.text);
		comment.setUser(user);
		cityService.addComment(cityName, comment);
		return "Okay";
	}

}
