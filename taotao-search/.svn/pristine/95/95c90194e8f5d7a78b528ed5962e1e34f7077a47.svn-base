package com.taotao.search.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;

@Repository
public class SearchDaoImpl implements SearchDao {

	@Autowired
	private HttpSolrClient.Builder builder;
	private SolrClient solr;
	
	@Override
	public SearchResult getResult(SolrQuery query) {
		SearchResult searchResult = null;
		try {
			searchResult = new SearchResult();
			List<Item> items = new ArrayList<Item>();
			solr = builder.build();
			QueryResponse queryResponse = solr.query(query);
			SolrDocumentList solrDocumentList = queryResponse.getResults();
			searchResult.setRecordCount(solrDocumentList.getNumFound());
			for (SolrDocument solrDocument : solrDocumentList) {
				Item item = new Item();
				item.setId((String) solrDocument.get("id"));
				item.setCategory_name((String) solrDocument.get("item_category_name"));
				item.setImage((String) solrDocument.get("item_image"));
				item.setPrice((long) solrDocument.get("item_price"));
				item.setSell_point((String) solrDocument.get("item_sell_point"));
				item.setTitle((String) solrDocument.get("item_title"));
				items.add(item);
			}
			searchResult.setItemList(items);
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
		return searchResult;
	}

}
