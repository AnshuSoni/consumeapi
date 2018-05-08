package com.tcs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GstRestController {

	//auth controller
	
	
	//module to be separated by action code, then do configure returns module

	@GetMapping(value="/return-filecount")
	public String returnFileCount() {
		return "hello";
	}
}
