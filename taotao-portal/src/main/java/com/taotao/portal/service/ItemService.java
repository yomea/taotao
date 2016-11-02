package com.taotao.portal.service;

import com.taotao.portal.pojo.ItemInfo;

public interface ItemService {
	
	ItemInfo showItemInfo(long itemId);
	String showItemDesc(long itemId);
	String showItemParam(long itemId);

}
