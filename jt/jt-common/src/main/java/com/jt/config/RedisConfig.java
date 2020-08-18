package com.jt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author huilong
 * @create 2020/8/12 16:03
 */
@Configuration
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {
    /*@Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private Integer port;
    @Bean
    public Jedis jedis(){


        return new Jedis(host,port);

    }*/

    @Value("${redis.nodes}")
    private String nodes;
    /**
     * spring整合redis分片机制
     */
    @Bean
    public ShardedJedis shardedJedis(){
        String[] strNodes = nodes.split(",");
        List<JedisShardInfo> shards = new ArrayList<>();
        for (String strNode : strNodes) {
            String host = strNode.split(":")[0];
            int port = Integer.parseInt(strNode.split(":")[1]);
            JedisShardInfo jedisShardInfo = new JedisShardInfo(host,port);
            shards.add(jedisShardInfo);
        }
        ShardedJedis shardedJedis = new ShardedJedis(shards);
        return shardedJedis;

    }
    @Bean
    public JedisCluster jedisCluster(){
        String[] strNodes = nodes.split(",");
        Set<HostAndPort> nodes = new HashSet<>();
        for (String strNode : strNodes) {
            String host = strNode.split(":")[0];
            int port = Integer.parseInt(strNode.split(":")[1]);
            HostAndPort hostAndPort = new HostAndPort(host,port);
            nodes.add(hostAndPort);

        }
        JedisCluster jedisCluster = new JedisCluster(nodes);
        return jedisCluster;
    }
}
