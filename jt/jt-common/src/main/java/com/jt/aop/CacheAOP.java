package com.jt.aop;

import com.jt.annotation.CacheFind;
import com.jt.util.ObjectMapperUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.util.Arrays;

/**
 * @Author huilong
 * @create 2020/8/14 11:05
 */
@Component
@Aspect
public class CacheAOP {

    @Autowired
    private JedisCluster jedis;


    /*//1、定义切入点表达式
    @Pointcut("bean(itemCatServiceImpl)")
    public void pointCut(){ }*/

    /*@Pointcut("@annotation(com.jt.annotation.CacheFind)")
    public void cachePointCut(){}*/

/*
    @Before("pointCut()")
    public void before(JoinPoint jp){
        System.out.println("前置通知");
        //获取目标方法的名称
//        jp.getSignature().getDeclaringType().getMethod();
        String declaringTypeName = jp.getSignature().getDeclaringTypeName();
        Object target = jp.getTarget();

        Object[] args = jp.getArgs();

        System.out.println(declaringTypeName + target + args);
    }*/


    /**
     * 动态获取注解参数的步骤：
     * 1、切入点表达式要求拦截一个类型为cacheFind注解
     * 2、并且利用连接点为参数中的cacheFind赋值
     * @param joinPoint
     * @param cacheFind
     * @return
     */
    @Around("@annotation(cacheFind)")
    public Object around(ProceedingJoinPoint joinPoint, CacheFind cacheFind){

        try {
            String prekey = cacheFind.key();
            String args = Arrays.toString(joinPoint.getArgs());
            String key = prekey + "::" +args;
            Object result = null;

            if (jedis.exists(key)) {

                MethodSignature methodsignature =(MethodSignature) joinPoint.getSignature();

                //从缓存中取数据
                result = ObjectMapperUtil.toObject(jedis.get(key), methodsignature.getReturnType());

            } else {
                result = joinPoint.proceed();
                //转成json串并添加缓存
                String json = ObjectMapperUtil.toJson(result);
                if(cacheFind.seconds()>0)
                    jedis.setex(key,cacheFind.seconds(),json);
                else
                    jedis.set(key, json);
            }

            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new RuntimeException(throwable.getMessage());
        }




    }

}
