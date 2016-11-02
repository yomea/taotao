package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService service;

	@RequestMapping("/item/{itemId}")
	public String getItemInfo(@PathVariable long itemId, Model model) {
		ItemInfo item = service.showItemInfo(itemId);
		
		model.addAttribute("item", item);
		
		return "item";
		
	}
	
	@RequestMapping(value="/item/desc/{itemId}", produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemDesc(@PathVariable long itemId) {
		String string = service.showItemDesc(itemId);
		
		
		return string;
	}
	
	@RequestMapping(value="/item/param/{itemId}", produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable long itemId) {
		String string = service.showItemDesc(itemId);
		
		
		return string;
	}
	
}
