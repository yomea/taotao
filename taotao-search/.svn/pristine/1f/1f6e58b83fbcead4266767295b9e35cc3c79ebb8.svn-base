package com.taotao.search.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService service;

	@RequestMapping("/search")
	@ResponseBody
	public TaotaoResult getSearchResult(@RequestParam("q") String queryStr, @RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="10") int rows) {
		if(queryStr == null || queryStr.trim().equals("")) {
			return TaotaoResult.build(400, "搜索内容不能为空！");
		}
		try {
			queryStr = new String(queryStr.getBytes("ISO8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		SearchResult searchResult = service.getResult(queryStr, page, rows);
		return TaotaoResult.ok(searchResult);

	}

}
