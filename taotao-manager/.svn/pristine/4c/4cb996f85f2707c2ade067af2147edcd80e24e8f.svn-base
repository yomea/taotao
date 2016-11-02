package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EUTreeNode> getContentCategoryList(long parentId) {
		List<EUTreeNode> nodes = new ArrayList<EUTreeNode>();
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		for (TbContentCategory tbContentCategory : list) {
			EUTreeNode node = new EUTreeNode(tbContentCategory.getId(), tbContentCategory.getName(),
					tbContentCategory.getIsParent() ? "closed" : "open");
			nodes.add(node);
		}

		return nodes;
	}

	@Override
	public TaotaoResult createContentCategory(TbContentCategory contentCategory) {
		contentCategory.setCreated(new Date());
		contentCategory.setStatus(1);
		contentCategory.setSortOrder(1);
		contentCategory.setIsParent(false);
		contentCategory.setUpdated(new Date());
		TbContentCategory contentCat = contentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
		contentCat.setIsParent(true);
		int rows1 = contentCategoryMapper.updateByPrimaryKey(contentCat);
		int rows2 = contentCategoryMapper.insert(contentCategory);
		if (rows1 > 0 && rows2 > 0) {
			System.out.println(contentCategory.getId());
			return TaotaoResult.ok(contentCategory);
		}
		return TaotaoResult.build(400, "添加失败！");
	}

	@Override
	public TaotaoResult delContentCategory(long id) {
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(contentCategory.getParentId());
		int count = contentCategoryMapper.countByExample(example);
		if(count == 1) {
			TbContentCategory contentCat = contentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
			contentCat.setIsParent(false);
			contentCategoryMapper.updateByPrimaryKey(contentCat);
		}
		contentCategoryMapper.deleteByPrimaryKey(id);
		this.delleaf(id);
		return TaotaoResult.ok();
	}

	private void delleaf(long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);

		List<TbContentCategory> contentCategory = contentCategoryMapper.selectByExample(example);

		for (TbContentCategory tbContentCategory : contentCategory) {
			if(tbContentCategory.getIsParent()) {
				delleaf(tbContentCategory.getId());
				
			}
			contentCategoryMapper.deleteByPrimaryKey(tbContentCategory.getId());
		}
		

	}

	@Override
	public TaotaoResult updateContentCategory(long id, String name) {
		
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		contentCategory.setName(name);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		int rows = contentCategoryMapper.updateByPrimaryKey(contentCategory);
		if(rows > 0) {
			return TaotaoResult.ok();
					
		}
		
		return TaotaoResult.build(400, "修改失败！");
	}

}
