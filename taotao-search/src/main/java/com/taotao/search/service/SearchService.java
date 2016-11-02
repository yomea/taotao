package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

public interface SearchService {

	SearchResult getResult(String queryStr, int page, int rows);
	
}
