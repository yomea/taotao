package com.taotao.solrCloud;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrCloudTest {

	@Test
	public void test1() throws Exception {
		// 使用zookeeper的地址访问
		String zkHost = "192.168.243.128:2181,192.168.243.128:2182,192.168.243.128:2183";
		// 创建集群客户端
		CloudSolrClient cloudSolrClient = new CloudSolrClient.Builder().withZkHost(zkHost).build();
		// 设置默认的索引库
		cloudSolrClient.setDefaultCollection("collection2");
		// 设置document
		SolrInputDocument document = new SolrInputDocument();
		// 创建这个document的结构
		document.addField("id", "test001");
		document.addField("name", "test");
		// 添加内容
		cloudSolrClient.add(document);
		// 提交
		cloudSolrClient.commit();
	}

	@Test
	public void delDocument() throws Exception {
		// 使用zookeeper的地址访问
		String zkHost = "192.168.243.128:2181,192.168.243.128:2182,192.168.243.128:2183";
		
		CloudSolrClient cloudSolrClient = new CloudSolrClient.Builder().withZkHost(zkHost).build();
	
		cloudSolrClient.setDefaultCollection("collection2");
		
		cloudSolrClient.deleteByQuery("*:*");
		
		cloudSolrClient.commit();
		
	}
	
	@Test
	public void query() throws Exception {
		// 使用zookeeper的地址访问
		String zkHost = "192.168.243.128:2181,192.168.243.128:2182,192.168.243.128:2183";
		
		CloudSolrClient cloudSolrClient = new CloudSolrClient.Builder().withZkHost(zkHost).build();
	
		cloudSolrClient.setDefaultCollection("collection2");
		
		SolrQuery query = new SolrQuery();
		
		query.setQuery("*:*");
		
		QueryResponse queryResponse = cloudSolrClient.query(query);
		
		SolrDocumentList list = queryResponse.getResults();
		
		for (SolrDocument solrDocument : list) {
			String name = (String) solrDocument.get("name");
			System.out.println(name);
		}
		
		
		cloudSolrClient.commit();
		
	}

}
