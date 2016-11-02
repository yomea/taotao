package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.portal.service.ContentService;

@Controller
public class IndexController {
	
	@Autowired
	private ContentService service;
	
	@RequestMapping("/index")
	public String showIndex(Model model) {
		
		String result = service.getContentList();
		
		model.addAttribute("ad1", result);
		
		return "index";
	}
	
	
}
