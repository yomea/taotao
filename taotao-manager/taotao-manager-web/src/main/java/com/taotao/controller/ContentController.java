package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDatagridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService service;

	@RequestMapping("/query/list")
	@ResponseBody
	public EUDatagridResult showContent(long categoryId, @RequestParam("page") int pageNum,
			@RequestParam("rows") int pageSize) {
		
		return service.getContent(categoryId, pageNum, pageSize);
		
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult saveContent(TbContent content) {
		
		return service.saveContent(content);
		
	}

}
