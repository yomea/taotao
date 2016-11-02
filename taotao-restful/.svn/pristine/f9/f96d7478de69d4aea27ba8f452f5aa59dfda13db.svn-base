package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemDescExample;
import com.taotao.pojo.TbItemDescExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemService;
import com.taotao.utils.JsonUtils;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${REDIS_ITEM_EXPIRE}")
	private int REDIS_ITEM_EXPIRE;

	@Override
	public TaotaoResult getItem(long itemId) {
		try {
			String result = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
			if (!StringUtils.isBlank(result)) {
				TbItem item = JsonUtils.jsonToPojo(result, TbItem.class);
				return TaotaoResult.ok(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbItem item = itemMapper.selectByPrimaryKey(itemId);

		try {
			String ok = jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":base", JsonUtils.objectToJson(item));
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":base", REDIS_ITEM_EXPIRE);
			if (!"ok".equalsIgnoreCase(ok)) {
				throw new Exception("缓存失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return TaotaoResult.ok(item);
	}

	@Override
	public TaotaoResult getItemDesc(long itemId) {

		try {
			String result = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
			if (!StringUtils.isBlank(result)) {
				TbItemDesc itemDesc = JsonUtils.jsonToPojo(result, TbItemDesc.class);
				return TaotaoResult.ok(itemDesc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbItemDescExample example = new TbItemDescExample();

		Criteria criteria = example.createCriteria();

		criteria.andItemIdEqualTo(itemId);

		List<TbItemDesc> itemDescs = itemDescMapper.selectByExampleWithBLOBs(example);

		if (itemDescs != null && itemDescs.size() > 0) {

			TbItemDesc itemDesc = itemDescs.get(0);

			try {
				String ok = jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":desc", JsonUtils.objectToJson(itemDesc));
				jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc", REDIS_ITEM_EXPIRE);
//				System.out.println(ok);
				if (!"ok".equalsIgnoreCase(ok)) {
					throw new Exception("缓存失败！");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return TaotaoResult.ok(itemDesc);

		}

		return TaotaoResult.build(400, "当前商品不存在!");
	}

	@Override
	public TaotaoResult getItemParam(long itemId) {
		try {
			String result = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
			if (!StringUtils.isBlank(result)) {
				TbItemParamItem itemParamItem = JsonUtils.jsonToPojo(result, TbItemParamItem.class);
				return TaotaoResult.ok(itemParamItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TbItemParamItemExample example = new TbItemParamItemExample();
		com.taotao.pojo.TbItemParamItemExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		
		List<TbItemParamItem> itemParamItem = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if(itemParamItem != null && itemParamItem.size() > 0) {
			
			try {
				String ok = jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param", JsonUtils.objectToJson(itemParamItem.get(0)));
				jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":param", REDIS_ITEM_EXPIRE);
				if (!"ok".equalsIgnoreCase(ok)) {
					throw new Exception("缓存失败！");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return TaotaoResult.ok(itemParamItem.get(0));
		}



		return TaotaoResult.build(400, "当前商品不存在!");
	}

}
