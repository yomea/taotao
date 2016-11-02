package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService service;
	
	@RequestMapping("/list/{contentCategoryId}")
	@ResponseBody
	public TaotaoResult showContentList(@PathVariable long contentCategoryId) {
		return service.selectContentList(contentCategoryId);
	}
	
}
