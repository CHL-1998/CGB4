package com.jt.test;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author huilong
 * @create 2020/8/15 16:14
 */
public class TestSentinel {

    /**
     * 参数说明：
     *  masterName: 主机名称
     *  sentinels: 哨兵节点信息
     */
    @Test
    public void test01(){
        Set<String> sentinels = new HashSet<>();
        sentinels.add("192.168.126.130:26379");
        JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster",sentinels);
        Jedis jedis = sentinelPool.getResource();
        jedis.set("哨兵","哨兵测试");
        System.out.println(jedis.get("哨兵"));


    }
}
