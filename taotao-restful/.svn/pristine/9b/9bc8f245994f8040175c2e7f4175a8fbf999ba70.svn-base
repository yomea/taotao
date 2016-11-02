package com.taotao.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrTest {
	
	@Test
	public void create() throws Exception { 
		String urlString = "http://192.168.181.128:8080/solr";
		SolrClient solr = new HttpSolrClient.Builder(urlString).build();
		//集群
		/*String zkHostString = "zkServerA:2181,zkServerB:2181,zkServerC:2181/solr";
		SolrClient solr = new CloudSolrClient.Builder().withZkHost(zkHostString).build();*/
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "552199");
		document.addField("name", "Gouda cheese wheel");
		document.addField("price", "49.99");
		solr.add(document);
		 
		// Remember to commit your changes!
		 
		solr.commit();
	}
	
	@Test
	public void delete() throws Exception {
		String urlString = "http://192.168.181.128:8080/solr";
		SolrClient solr = new HttpSolrClient.Builder(urlString).build();
		solr.deleteById("552199");
		solr.deleteByQuery("id:100");
		solr.commit();
	}
	
	@Test
	public void query() throws Exception {
		String urlString = "http://192.168.181.128:8080/solr";
		SolrClient solr = new HttpSolrClient.Builder(urlString).build();
		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		query.setStart(20);
		query.setRows(50);
		//增加高亮域
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		query.addHighlightField("item_title");
		QueryResponse queryResponse = solr.query(query);
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println("------------------------------------");
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_category_name"));
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println("------------------------------------");
		}
	}
	

}
