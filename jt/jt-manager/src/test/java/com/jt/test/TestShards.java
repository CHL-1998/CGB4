package com.jt.test;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author huilong
 * @create 2020/8/15 10:23
 */
public class TestShards { //该类表示测试redis分片机制
    /**
     * 实现数据的存储
     */
    @Test
    public void test01(){
        List<JedisShardInfo> shards = new ArrayList<>();
        shards.add(new JedisShardInfo("192.168.126.130",6379));
        shards.add(new JedisShardInfo("192.168.126.130",6380));
        shards.add(new JedisShardInfo("192.168.126.130",6381));
        ShardedJedis shardedJedis = new ShardedJedis(shards);
        shardedJedis.set("1245","454518");
        shardedJedis.set("1258","457898918");
        shardedJedis.set("1253","45785");
        System.out.println(shardedJedis.get("1245"));

    }
}
