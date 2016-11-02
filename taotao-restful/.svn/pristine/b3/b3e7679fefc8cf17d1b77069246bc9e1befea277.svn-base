package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Controller
@RequestMapping("/itemcat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;

	//第一种方法
	// produces="application/json;charset=utf-8"
	/*@RequestMapping(value = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String getCatResult(String callback) {
		CatResult catResult = itemCatService.getCatResultList();
		String jsonStr = JsonUtils.objectToJson(catResult);
		jsonStr = callback + "(" + jsonStr + ")";
		return jsonStr;
	}*/

	//第二种方法
	@RequestMapping("/all")
	@ResponseBody
	public Object getItemCatList(String callback) {
		CatResult catResult = itemCatService.getCatResultList();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}

}
