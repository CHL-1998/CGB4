package com.jt.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.params.SetParams;

import java.util.Map;

/**
 * @Author huilong
 * @create 2020/8/12 14:06
 */
@SpringBootTest
public class TestRedis {

    @Autowired
    private Jedis jedis;
    @Test
    public void test01(){
        String host = "192.168.126.130";
        //Jedis jedis = new Jedis(host,6379);
        jedis.set("2004","redis测试1");
        String value = jedis.get("2004");
        System.out.println(value);

    }

    @Test
    public void test02(){
        Jedis jedis = new Jedis("192.168.126.130",6379);

        if(jedis.exists("2004")){
            jedis.expire("2004",100);

            try{
                Thread.sleep(2000);
                Long time = jedis.ttl("2004");
                System.out.println(time);
                jedis.persist("2004");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    /**
     * 需求说明：
     * key已经存在则不允许赋值
     */
    @Test
    public void test03() {
        Jedis jedis = new Jedis("192.168.126.130", 6379);
        jedis.setnx("boss","潘石屹");
        jedis.setnx("boss","马化腾");
        jedis.setnx("boss","马云");
        System.out.println(jedis.get("boss"));
    }

    /**
     * 原子性：一起完成一起回滚
     * 锁机制：保证原子性  死锁
     */
    @Test
    public void test04() {
        Jedis jedis = new Jedis("192.168.126.130", 6379);
        jedis.setex("b",20,"aaa");//满足原子性需求
    }
    @Test
    public void test05() {
        Jedis jedis = new Jedis("192.168.126.130", 6379);
        SetParams setParams = new SetParams();
        setParams.nx().ex(20);
        jedis.set("aaaa","88888",setParams);
    }
    @Test
    public void test06() {
        Jedis jedis = new Jedis("192.168.126.130", 6379);

        jedis.hset("person","name","tomcat");
        jedis.hset("person","age","18"  );
        Map<String, String> person = jedis.hgetAll("person");
        System.out.println(person);
    }

    @Test
    public void testList(){

        Jedis jedis = new Jedis("192.168.126.130", 6379);
        jedis.lpush("list","1","2","3","4","5");
        System.out.println(jedis.rpop("list"));
    }

    @Test
    public void testMulti(){
        Jedis jedis = new Jedis("192.168.126.130", 6379);
        Transaction transaction = jedis.multi();
        try{
            transaction.set("aaaab","134");
            transaction.set("bbbbdd","134");
            transaction.exec();
        }catch (Exception e){
            e.printStackTrace();
            transaction.discard();
        }
    }
}
