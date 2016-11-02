package com.taotao.rest.redis;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
 
	@Test
	public void jedis() {
		Jedis jedis = new Jedis("192.168.243.128", 6379);
//		jedis.auth("root");
		jedis.set("hello", "world");
		System.out.println(jedis.get("hello"));
		jedis.close();
	}
	 
	@Test
	public void jedisPool() { 
		JedisPool jedisPool = new JedisPool("192.168.243.128", 6379);
		Jedis jedis = jedisPool.getResource();
//		jedis.auth("root");
		jedis.set("youth", "hong");
		System.out.println(jedis.get("youth"));
		jedis.close();
		jedisPool.close();
	}
	
	@Test
	public void jedisCluster() {
		
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.243.128", 7001));
		nodes.add(new HostAndPort("192.168.243.128", 7002));
		nodes.add(new HostAndPort("192.168.243.128", 7003));
		nodes.add(new HostAndPort("192.168.243.128", 7004));
		nodes.add(new HostAndPort("192.168.243.128", 7005));
		nodes.add(new HostAndPort("192.168.243.128", 7006));
		
		JedisCluster jedisCluster = new JedisCluster(nodes);
		
		jedisCluster.set("isJedisCluster", "yes");
		System.out.println(jedisCluster.get("isJedisCluster"));
		try {
			if(jedisCluster != null) {
				jedisCluster.close();
				jedisCluster = null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
