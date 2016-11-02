package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUDatagridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface TbItemParamService {

	EUDatagridResult selectTbItemParam(int pageNum, int pageSize);
	
	TaotaoResult selectTbItemParamByCid(long cid);
	
	TaotaoResult saveItemParam(TbItemParam record);
	
	TaotaoResult deleteItemParam(List<Long> ids);
	
}
