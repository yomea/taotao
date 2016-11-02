package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContentCategory;
import com.taotao.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService service;

	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> showContentCatList(@RequestParam(value = "id", defaultValue = "0") long parentId) {
		return service.getContentCategoryList(parentId);
	}

	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createContentCat(TbContentCategory contentCategory) {
	
		return service.createContentCategory(contentCategory);
		
	}
	
	@RequestMapping("/delete/")
	@ResponseBody
	public TaotaoResult delContentCat(long id) {
		return service.delContentCategory(id);
		
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult updateContentCat(long id, String name) {
		return service.updateContentCategory(id, name);
		
	}

}
