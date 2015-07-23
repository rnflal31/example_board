package com.samhyun.study.main.web;

import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController {

	Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String list(HttpServletResponse response, Locale locale, Model model) throws Exception {
		return "/main/home";		
	}	
}
