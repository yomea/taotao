package com.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import com.taotao.utils.HttpClientUtil;

@Service
public class SearchServiceImpl implements SearchService {

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
	
	@Override
	public SearchResult search(String queryStr, int page) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("q", queryStr);
		params.put("page", page+"");
		String result = HttpClientUtil.doGet(SEARCH_BASE_URL, params);
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(result, SearchResult.class);
		
		return (SearchResult) taotaoResult.getData();
	}

}
