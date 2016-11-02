package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDatagridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.TbItemService;

@Controller
public class ItemController {
	
	@Autowired
	private TbItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		return itemService.selectByPrimaryKey(itemId);
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDatagridResult getItemList(@RequestParam("page") int pageNum, @RequestParam("rows") int pageSize) {
		return itemService.listItems(pageNum, pageSize);
	}
	
	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createItem(TbItem item, String desc, String itemParams) {
		return itemService.creatItem(item, desc, itemParams);
	}
	
}
