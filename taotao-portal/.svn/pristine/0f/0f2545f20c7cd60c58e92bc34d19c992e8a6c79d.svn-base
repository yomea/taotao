package com.taotao.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;

@Service
public class ItemServiceImpl implements ItemService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	@Value("${ITEM_DESC_URL}")
	private String ITEM_DESC_URL;
	@Value("${ITEM_PARAM_URL}")
	private String ITEM_PARAM_URL;

	@Override
	public ItemInfo showItemInfo(long itemId) {
		String jsonData = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(jsonData, ItemInfo.class);
		if (taotaoResult.getStatus() == 200) {
			return (ItemInfo) taotaoResult.getData();

		}
		return null;
	}

	@Override
	public String showItemDesc(long itemId) {

		String result = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + itemId);
		System.out.println(result);
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(result, TbItemDesc.class);
		if (taotaoResult.getStatus() == 200) {

			TbItemDesc itemDesc = (TbItemDesc) taotaoResult.getData();
			String string = itemDesc.getItemDesc();
			return string;
		}

		return null;
	}

	@Override
	public String showItemParam(long itemId) {
		String result = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + itemId);
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(result, TbItemParamItem.class);
		TbItemParamItem itemParamItem = (TbItemParamItem) taotaoResult.getData();
		String jsonData = itemParamItem.getParamData();
		// 生成html
		// 把规格参数json数据转换成java对象
		List<Map> jsonList = JsonUtils.jsonToPojo(jsonData, List.class);
		StringBuffer sb = new StringBuffer();
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
		sb.append("    <tbody>\n");
		for (Map m1 : jsonList) {
			sb.append("        <tr>\n");
			sb.append("            <th class=\"tdTitle\" colspan=\"2\">" + m1.get("group") + "</th>\n");
			sb.append("        </tr>\n");
			List<Map> list2 = (List<Map>) m1.get("params");
			for (Map m2 : list2) {
				sb.append("        <tr>\n");
				sb.append("            <td class=\"tdTitle\">" + m2.get("k") + "</td>\n");
				sb.append("            <td>" + m2.get("v") + "</td>\n");
				sb.append("        </tr>\n");
			}
		}
		sb.append("    </tbody>\n");
		sb.append("</table>");
		
		return sb.toString();
	}

}
