package com.jt.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Author huilong
 * @create 2020/8/12 17:21
 */
public class ObjectMapperUtil {
    //创建工具api的对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //封装api，将对象转化为json
    public static String toJson(Object object){

        if(object==null)throw  new RuntimeException("传入对象不能为空");
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    /**
     * 将json的串转对象
     */
    public static <T> T toObject(String json,Class<T> target){
        if(json ==null || "".equals(json) || target == null)
            throw new RuntimeException("参数不能为null");

        try {
            T  t  =  MAPPER.readValue(json, target);
            return t;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }
}
