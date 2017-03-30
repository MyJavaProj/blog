package com.blog.common;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import com.blog.dao.BaseTest;

public class redisTest extends BaseTest {

	JedisPool jedisPool = JedisPoolUtil.instance();
	@Test
	public void connectTest() {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully");
		// 查看服务是否运行
		System.out.println("Server is running: " + jedis.ping());

		// //Redis Java String(字符串) 实例
		// // 设置 redis 字符串数据
		// jedis.set("w3ckey", "Redis tutorial");
		// // 获取存储的数据并输出
		// System.out.println("Stored string in redis:: " +
		// jedis.get("w3ckey"));

		// // Redis Java List(列表) 实例
		// // 存储数据到列表中
		// jedis.lpush("tutorial-list", "Redis");
		// jedis.lpush("tutorial-list", "Mongodb");
		// jedis.lpush("tutorial-list", "Mysql");
		// // 获取存储的数据并输出
		// List<String> list = jedis.lrange("tutorial-list", 0, 5);
		// for (int i = 0; i < list.size(); i++) {
		// System.out.println("Stored string in redis:: " + list.get(i));
		// }

		// Redis Java Keys 实例
		// 获取数据并输出
		 Set<String> list2 = jedis.keys("*");//jedis.keys("*");
		 for (@SuppressWarnings("rawtypes")
		Iterator iterator = list2.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println("List2 of stored keys::" + string);
		}
		 
		 jedis.close();
	}
	
	@Test
	/**
	 *事务测试  watch 
	 * 
	 */
	public void JedisTranSaction(){
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.watch("k1");
		Transaction transaction = jedis.multi();
		transaction.set("k1","v1");
		transaction.set("k2","v2");
		
		transaction.exec();
//		transaction.discard();
		System.out.println("=======ok======");
		
		jedis.close();
	}

	@Test
	public void JedisSlaveTest() throws InterruptedException{
		
		Jedis jedisMaster = jedisPool.getResource();//new Jedis("127.0.0.1", 6379);
		Jedis jedisSlave = new Jedis("127.0.0.1", 6380);
		try {
			jedisSlave.slaveof("127.0.0.1", 6379);
			jedisMaster.set("k3","v3");
			System.out.println("jedisMaster  k1:" + jedisMaster.get("k1") + ",k3:" +jedisMaster.get("k3"));
			Thread.sleep(1000);
			
			System.out.println("jedisSlave k3:" + jedisSlave.get("k3"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JedisPoolUtil.release(jedisPool, jedisMaster);
			jedisSlave.close();
		}
	}
	
	@Autowired 
	RedisTemplate<String, Object> redisTemplate;
	
	@Test
	public void JedisTemplate(){
		@SuppressWarnings("unchecked")
		Set<Integer> set = (Set<Integer>) redisTemplate.opsForHash().get("hashkey", "whitelist");
		if(set == null) set = new HashSet<Integer>();
		set.add(111);
		redisTemplate.opsForHash().put("hashkey", "whitelist", set);
		
		System.out.println("===1===");
	}
	
	// 测试RedisTemplate,自主处理key的可读性(String序列号)
    @Ignore
    @Test
    public void test4() {
        String key = "spring";
        ListOperations<String, Object> lop = redisTemplate.opsForList();
        RedisSerializer<String> serializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(serializer);
        // rt.setDefaultSerializer(serializer);
 
        lop.leftPush(key, "aaa");
        lop.leftPush(key, "bbb");
        long size = lop.size(key); // rt.boundListOps(key).size();
        Assert.assertEquals(2, size);
    }
 
    public static StringRedisTemplate stringRedisTemplate;
    // 测试便捷对象StringRedisTemplate
    @Ignore
    @Test
    public void test5() {
        ValueOperations<String, String> vop = stringRedisTemplate.opsForValue();
        String key = "string_redis_template";
        String v = "use StringRedisTemplate set k v";
        vop.set(key, v);
        String value = vop.get(key);
        Assert.assertEquals(v, value);
    }
}
