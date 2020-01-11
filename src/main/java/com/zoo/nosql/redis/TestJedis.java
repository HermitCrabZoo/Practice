package com.zoo.nosql.redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Transaction;

import java.util.*;

@Slf4j
public class TestJedis {


    public static void main(String[] args) {
//		jedisPool();
        api();
//		masterSlave();
//		transaction();
//		transactionCAS();
    }

    public static void get() {
        JedisCluster cluster = JedisPooler.cluster();
        log.info("k1:{}", cluster.get("k1"));
    }

    /**
     * Jedis池
     */
    public static void jedisPool() {
        Jedis jedis = JedisPooler.pool().getResource();
        jedis.set("jedisPool", "success");
        jedis.close();
    }

    /**
     * 常用api
     */
    public static void api() {
        try (Jedis jedis = JedisPooler.pool().getResource()) {
            Set<String> keys = jedis.keys("*");
            for (Iterator<String> iterator = keys.iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                System.out.println(key);
            }
            System.out.println("jedis.exists====>" + jedis.exists("k2"));
            System.out.println(jedis.ttl("k1"));
            //String
            //jedis.append("k1","myreids");
            System.out.println(jedis.get("k1"));
            jedis.set("k4", "k4_redis");
            System.out.println("----------------------------------------");
            jedis.mset("str1", "v1", "str2", "v2", "str3", "v3");
            System.out.println(jedis.mget("str1", "str2", "str3"));
            //list
            System.out.println("----------------------------------------");
            jedis.lpush("mylist", "v1", "v2", "v3", "v4", "v5");
            List<String> list = jedis.lrange("mylist", 0, -1);
            for (String element : list) {
                System.out.println(element);
            }
            //set
            jedis.sadd("orders", "jd001");
            jedis.sadd("orders", "jd002");
            jedis.sadd("orders", "jd003");
            Set<String> set1 = jedis.smembers("orders");
            for (Iterator<String> iterator = set1.iterator(); iterator.hasNext(); ) {
                String string = (String) iterator.next();
                System.out.println(string);
            }
            jedis.srem("orders", "jd002");
            System.out.println(jedis.smembers("orders").size());
            //hash
            jedis.hset("hash1", "userName", "lisi");
            System.out.println(jedis.hget("hash1", "userName"));
            Map<String, String> map = new HashMap<>();
            map.put("telphone", "13811814763");
            map.put("address", "atguigu");
            map.put("email", "abc@163.com");
            jedis.hmset("hash2", map);
            List<String> result = jedis.hmget("hash2", "telphone", "email");
            for (String element : result) {
                System.out.println(element);
            }
            //zset
            jedis.zadd("zset01", 60d, "v1");
            jedis.zadd("zset01", 70d, "v2");
            jedis.zadd("zset01", 80d, "v3");
            jedis.zadd("zset01", 90d, "v4");

            Set<String> s1 = jedis.zrange("zset01", 0, -1);
            for (Iterator<String> iterator = s1.iterator(); iterator.hasNext(); ) {
                String string = (String) iterator.next();
                System.out.println(string);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 主从复制
     */
    public static void masterSlave() {
        Jedis jedis = new Jedis("192.168.1.46", 6379);
        jedis.slaveof("192.168.1.26", 6379);
        jedis.close();
    }

    /**
     * 事务
     */
    public static void transaction() {
        //事务(半支持)
        try (Jedis jedis = JedisPooler.pool().getResource()) {
            Transaction transaction = jedis.multi();
            transaction.set("jedisPool", "error");//成功
            transaction.incr("k1");//失败
            transaction.set("k2", "v23");//成功
            transaction.exec();
//			transaction.discard();//取消事务
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 事务，比较
     *
     * @return
     */
    public static boolean transactionCAS() {
        //事务(半支持)
        try (Jedis jedis = JedisPooler.pool().getResource()) {
            int balance;// 可用余额
            int debt;// 欠额
            int amtToSubtract = 10;// 实刷额度

            jedis.watch("balance");
            //jedis.set("balance","5");//此句不该出现，讲课方便。模拟其他程序已经修改了该条目
            Thread.sleep(7000);
            balance = Integer.parseInt(jedis.get("balance"));
            if (balance < amtToSubtract) {
                jedis.unwatch();
                System.out.println("modify");
            } else {
                System.out.println("***********transaction");
                Transaction transaction = jedis.multi();
                transaction.decrBy("balance", amtToSubtract);
                transaction.incrBy("debt", amtToSubtract);
                transaction.exec();
                balance = Integer.parseInt(jedis.get("balance"));
                debt = Integer.parseInt(jedis.get("debt"));

                System.out.println("*******" + balance);
                System.out.println("*******" + debt);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
