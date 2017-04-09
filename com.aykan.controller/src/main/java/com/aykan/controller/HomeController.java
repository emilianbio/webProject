package com.aykan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(){
		return new ModelAndView("homePage");
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginUserForm(){
		return new ModelAndView("login");
	}
	
}
