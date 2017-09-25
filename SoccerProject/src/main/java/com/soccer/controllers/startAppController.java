package com.soccer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class startAppController {

	@RequestMapping(value = "/Adminstractor", method = RequestMethod.GET)
	public String homeAdminpage() {
		return "Admin/index";
	}
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	ModelAndView home(ModelAndView modelAndView) {
		System.err.println("HOME");
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
}
