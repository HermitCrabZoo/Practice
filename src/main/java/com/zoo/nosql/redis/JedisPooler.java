package com.zoo.nosql.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

public class JedisPooler {
    private JedisPooler() {
    }

    public static JedisPool pool() {
        return Pooler.pool;
    }

    private static class Pooler {
        private static JedisPool pool = init();

        private static JedisPool init() {
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setMaxTotal(1000);//最大实例
            poolConfig.setMaxIdle(32);//最大空闲
            poolConfig.setMaxWaitMillis(100 * 1000);//获取实例时最多等待的时间100秒,超时没获取到则抛异常
            poolConfig.setTestOnBorrow(true);//获取实例时测试实例的连通性，保证获取到的所有实例都是可用的
            JedisPool p = new JedisPool(poolConfig, "192.168.2.12", 6379);
            return p;
        }
    }


    public static JedisCluster cluster() {
        // 添加集群的服务节点Set集合
        // 添加节点
        Set<HostAndPort> hostAndPortsSet = Set.of(
        		new HostAndPort("192.168.2.25", 6379),
				new HostAndPort("192.168.2.25", 6380),
                new HostAndPort("192.168.2.25", 6381),
                new HostAndPort("192.168.2.25", 6382),
                new HostAndPort("192.168.2.25", 6383),
                new HostAndPort("192.168.2.25", 6384));

        // Jedis连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(100);
        // 最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(500);
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(0);
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(2000); // 设置2秒
        //对拿到的connection进行validateObject校验
        jedisPoolConfig.setTestOnBorrow(true);
        return new JedisCluster(hostAndPortsSet, jedisPoolConfig);
    }
}
