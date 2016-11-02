package com.taotao.rest.redis;
 
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class RedisTest {

	@Test
	public void redisTest() { 
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool jedisPool = applicationContext.getBean("redisClient", JedisPool.class);
		Jedis jedis = jedisPool.getResource();
		String str = jedis.get("hello");
		System.out.println(str);
		
	}
	@Test
	public void redisClusterTest() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisCluster jedisCluster = applicationContext.getBean("redisClient", JedisCluster.class);
		
		String str = jedisCluster.get("isJedisCluster");
		System.out.println(str);
		
	}
	
}
