package com.taotao.service;

import com.taotao.common.pojo.EUDatagridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface TbItemService {
	
	TbItem selectByPrimaryKey(Long id);
	
	EUDatagridResult listItems(int pageNum, int pageSize);
	
	TaotaoResult creatItem(TbItem item, String desc, String itemParams);
	
}
