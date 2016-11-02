package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.CatNode;
import com.taotao.common.pojo.CatResult;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemCatService;
import com.taotao.utils.JsonUtils;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Autowired
	private JedisClient jedisClient;

	@Override
	public CatResult getCatResultList() {

		try {
			String result = jedisClient.get("catResult");
			if(!StringUtils.isBlank(result)) {
				return JsonUtils.jsonToPojo(result, CatResult.class);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		CatResult catResult = new CatResult();

		catResult.setData(this.getCatNodeList(0));

		try {
			jedisClient.set("catResult", JsonUtils.objectToJson(catResult));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return catResult;
	}

	private List<?> getCatNodeList(long pid) {

		TbItemCatExample example = new TbItemCatExample();

		Criteria criteria = example.createCriteria();

		criteria.andParentIdEqualTo(pid);

		List<TbItemCat> itemCat = itemCatMapper.selectByExample(example);

		List<Object> catNodes = new ArrayList<Object>();

		int count = 0;

		for (TbItemCat tbItemCat : itemCat) {

			if (pid == 0) {
				count++;
			}

			if (tbItemCat.getIsParent()) {

				CatNode catNode = new CatNode();

				catNode.setName("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
				if (pid == 0) {
					catNode.setUrl("/products/" + tbItemCat.getId() + ".html");

				} else {
					catNode.setUrl(tbItemCat.getName());
				}

				catNode.setItem(this.getCatNodeList(tbItemCat.getId()));

				catNodes.add(catNode);

			} else {
				catNodes.add("/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName());

			}
			if (count >= 14) {
				break;
			}
		}

		return catNodes;

	}

}
