package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDatagridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.TbItemService;
import com.taotao.utils.IDUtils;

@Service
public class TbItemSericeImpl implements TbItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper; 

	@Override
	public TbItem selectByPrimaryKey(Long id) {
		// itemMapper.selectByPrimaryKey(id);
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {

			return list.get(0);
		}
		return null;
	}

	@Override
	public EUDatagridResult listItems(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		TbItemExample example = new TbItemExample();
		// Criteria criteria = example.createCriteria();
		List<TbItem> list = itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		EUDatagridResult datagrid = new EUDatagridResult(pageInfo.getTotal(), list);
		return datagrid;
	}

	@Override
	public TaotaoResult creatItem(TbItem item, String desc, String itemParams) {
		Long id = IDUtils.genItemId();
		item.setId(id);
		item.setCreated(new Date());
		item.setStatus((byte) 1);
		item.setUpdated(new Date());
		int rows1 = itemMapper.insert(item);
		int rows2 = this.insertItemDesc(id, desc);
		int rows3 = this.insertItemParamItem(id, itemParams);
		if (rows1 <= 0 || rows2 <= 0 || rows3 <= 0) {
			throw new RuntimeException();
		}
		return TaotaoResult.ok();
	}
	
	/**
	 * 保存商品的同时保存商品描述
	 * @param itemId
	 * @param desc
	 * @return
	 */
	public int insertItemDesc(Long itemId, String desc) {
		TbItemDesc record = new TbItemDesc();
		record.setItemId(itemId);
		record.setItemDesc(desc);
		record.setCreated(new Date());
		record.setUpdated(new Date());
		int rows = itemDescMapper.insert(record);
		
		return rows;
	}
	
	/**
	 * 保存item同时保存规格参数
	 * @param itemId
	 * @param itemParams
	 * @return
	 */
	public int insertItemParamItem(long itemId, String itemParams) {
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParams);
		int rows = itemParamItemMapper.insert(itemParamItem);
		
		return rows;
		
		
	}

}
