package com.taotao.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDao searchDao;

	@Override
	public SearchResult getResult(String queryStr, int page, int rows) {
		SolrQuery query = new SolrQuery();
		query.setQuery(queryStr);
		// 设置索搜开始时间
		query.setStart((page - 1) * rows);
		query.setRows(rows);
		// 设置默认搜索域
		query.set("df", "item_keywords");
		// 设置高亮显示
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		SearchResult result = searchDao.getResult(query);
		// 计算总页数
		long recordCount = result.getRecordCount();
		long pageCount = recordCount % rows == 0 ? recordCount / rows : recordCount / rows + 1;
		result.setPageCount(pageCount);
		result.setCurPage(page);
		return result;
	}

}
