package com.blog.common;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import com.blog.dao.BaseTest;

public class redis_jedisClusterTest extends BaseTest {

	@Test
	public void jedisClusterTest() {
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		jedisClusterNodes.add(new HostAndPort("192.168.1.103", 6379));
		JedisCluster jedis = new JedisCluster(jedisClusterNodes);
		
		
//		jedis.set("key", "v3");

//		String key = jedis.get("key");
		// 这里的jc不需要关闭，因为内部已经关闭连接了。

//		System.out.println("key :" + key);

		long begin = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			jedis.set("person." + i + ".name", "frank");
			jedis.set("person." + i + ".city", "beijing");
			String name = jedis.get("person." + i + ".name");
			String city = jedis.get("person." + i + ".city");
			assertEquals("frank", name);
			assertEquals("beijing", city);
			
//			jedis.del("person." + i + ".city");
//			jedis.del("person." + i + ".name");
			Boolean result = jedis.exists("person." + i + ".name");
			assertEquals(true, result);
			
//			result = jedis.exists("person." + i + ".city");
//			assertEquals(false, result);
		}
		long end = System.currentTimeMillis();
		System.out.println("total time: " + (end - begin) / 1000);

	}
}
