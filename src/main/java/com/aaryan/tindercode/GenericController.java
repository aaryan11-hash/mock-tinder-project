package com.aaryan.tindercode;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class GenericController {

	@RequestMapping("/")
	public String firstpage() {
		
		return "HOME";
	}
}
