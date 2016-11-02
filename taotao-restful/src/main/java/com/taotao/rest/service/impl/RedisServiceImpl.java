package com.taotao.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Override
	public TaotaoResult synContent(long contentId) {
		long result = 0;
		try {
			result = jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentId + "");
		} catch (Exception e) {
			return TaotaoResult.build(500, "同步失败！");
		}
		if(result == 0) {
			return TaotaoResult.build(500, "同步失败！");
		}
		
		return TaotaoResult.ok();
	}

}
