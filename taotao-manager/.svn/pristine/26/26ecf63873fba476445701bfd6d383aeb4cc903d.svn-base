package com.taotao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItemParam;
import com.taotao.service.TbItemParamService;
import com.taotao.utils.JsonUtils;

@Controller
@RequestMapping("/item")
public class ItemParamController {

	@Autowired
	private TbItemParamService itemParamService;

	@RequestMapping("/param/list")
	@ResponseBody
	public String selectTbItemParam(@RequestParam("page") int pageNum, @RequestParam("rows") int pageSize) {
		
		return JsonUtils.objectToJson(itemParamService.selectTbItemParam(pageNum, pageSize));
		

	}
	
	
	@RequestMapping("/param/query/itemcatid/{itemcatid}")
	@ResponseBody
	public String selectTbItemParamByCid(@PathVariable("itemcatid") long cid) {
		
		return JsonUtils.objectToJson(itemParamService.selectTbItemParamByCid(cid));
		

	}
	
	@RequestMapping("/param/save/{cid}")
	@ResponseBody
	public String saveTbItemParam(@PathVariable long cid, TbItemParam itemParam) {
		
		itemParam.setItemCatId(cid);
		
		return JsonUtils.objectToJson(itemParamService.saveItemParam(itemParam));
		

	}
	
	@RequestMapping("/param/delete")
	@ResponseBody
	public String saveTbItemParam(@RequestParam("ids") String str) {
		List<Long> ids = new ArrayList<Long>();
		String[] strArr = str.split(",");
		for (String id : strArr) {
			ids.add(Long.valueOf(id));
		}
		return JsonUtils.objectToJson(itemParamService.deleteItemParam(ids));
		

	}

}
