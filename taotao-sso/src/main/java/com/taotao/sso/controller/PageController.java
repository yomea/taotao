package com.taotao.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

	
	@RequestMapping(value="/page/register", method=RequestMethod.GET)
	public String register() {
		return "register";
	}
	
	@RequestMapping(value="/page/login", method=RequestMethod.GET)
	public String login(String redirect, Model model) {
		if(redirect != null && !StringUtils.isBlank(redirect)) {
			model.addAttribute("redirect", redirect);
		}
		return "login";
	}
	
}
