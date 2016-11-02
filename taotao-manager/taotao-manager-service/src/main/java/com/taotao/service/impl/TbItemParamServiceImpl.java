package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDatagridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.TbItemParamService;

@Service
public class TbItemParamServiceImpl implements TbItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Override
	public EUDatagridResult selectTbItemParam(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		TbItemParamExample example = new TbItemParamExample();
		List<TbItemParam> itemParams = itemParamMapper.selectByExample(example);
		PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(itemParams);
		long total = pageInfo.getTotal();
		System.out.println(itemParams);
		EUDatagridResult datagrid = null;
		if (itemParams == null || itemParams.size() <= 0) {
			return datagrid = new EUDatagridResult(total, null);
		}
		datagrid = new EUDatagridResult(total, itemParams);

		return datagrid;
	}

	@Override
	public TaotaoResult selectTbItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> itemParams = itemParamMapper.selectByExampleWithBLOBs(example);
		if(itemParams != null && itemParams.size() > 0) {
			return TaotaoResult.ok(itemParams.get(0));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult saveItemParam(TbItemParam record) {
		
		record.setCreated(new Date());
		record.setUpdated(new Date());
		
		int rows = itemParamMapper.insert(record);
		System.out.println(rows);
		if(rows > 0) {
			return TaotaoResult.ok();
		}
		
		return TaotaoResult.build(400, "添加失败！");
	}

	@Override
	public TaotaoResult deleteItemParam(List<Long> ids) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);
		int rows = itemParamMapper.deleteByExample(example);
		if(rows > 0) {
			return TaotaoResult.ok();
		}
		return TaotaoResult.build(400, "删除失败！");
	}

}
