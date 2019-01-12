package com.zoo.nosql.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPooler {
	private JedisPooler() {}
	
	public static JedisPool pool() {
		return Pooler.pool;
	}
	
	private static class Pooler{
		private static JedisPool pool=init();
		private static JedisPool init() {
			JedisPoolConfig poolConfig=new JedisPoolConfig();
			poolConfig.setMaxTotal(1000);//最大实例
			poolConfig.setMaxIdle(32);//最大空闲
			poolConfig.setMaxWaitMillis(100*1000);//获取实例时最多等待的时间100秒,超时没获取到则抛异常
			poolConfig.setTestOnBorrow(true);//获取实例时测试实例的连通性，保证获取到的所有实例都是可用的
			JedisPool p=new JedisPool(poolConfig, "192.168.2.12", 6379);
			return p;
		}
	}
}
