package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;

public interface TbItemCatService {
	
	List<EUTreeNode> getTreeNode(Long parentId);
	
}
