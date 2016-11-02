package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

public interface ContentService {

	TaotaoResult selectContentList(long contentCategoryId);
	
}
