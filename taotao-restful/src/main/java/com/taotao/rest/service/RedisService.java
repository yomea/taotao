package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

public interface RedisService {

	TaotaoResult synContent(long contentId);
	
}
