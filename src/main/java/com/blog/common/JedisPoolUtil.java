package com.blog.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	private static volatile JedisPool jedisPool = null;
	private JedisPoolUtil() {
		
	}
	
	public static JedisPool instance() {
		if (null ==jedisPool) {
			synchronized (JedisPoolUtil.class) {
				if (null == jedisPool) {
					JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
					jedisPoolConfig.setMaxIdle(32);
					jedisPoolConfig.setMaxWaitMillis(100*1000);
					jedisPoolConfig.setTestOnBorrow(true);
					
					jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
				}
			}
		}
		
		return jedisPool;
	}
	
	public static void release(JedisPool jedisPool,Jedis jedis) {
//		jedisPool.returnResourceObject(jedis);
		jedis.close();
		jedisPool.destroy();
	}
}
