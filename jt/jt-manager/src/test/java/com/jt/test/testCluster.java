package com.jt.test;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author huilong
 * @create 2020/8/17 9:39
 */
public class testCluster {

    @Test
    public void test01(){
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.126.130",7000));
        nodes.add(new HostAndPort("192.168.126.130",7001));
        nodes.add(new HostAndPort("192.168.126.130",7002));
        nodes.add(new HostAndPort("192.168.126.130",7003));
        nodes.add(new HostAndPort("192.168.126.130",7004));
        nodes.add(new HostAndPort("192.168.126.130",7005));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("滚蛋","redis集群测试");
        System.out.println(jedisCluster.get("滚蛋"));
    }
}
