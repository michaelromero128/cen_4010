package com.example.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

public class MyErrorController implements ErrorController{
	
	@RequestMapping("/error")
	public String handleError() {
		System.out.println("LOLOLOLOLOLOLOLOLOLOLOLOLOL");
		return "error";
	}
	@Override
	public String getErrorPath() {
		return null;
	}

}
