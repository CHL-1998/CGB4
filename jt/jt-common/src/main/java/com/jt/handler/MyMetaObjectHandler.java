package com.jt.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author huilong
 * @create 2020/8/4 17:28
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setInsertFieldValByName("created",new Date(),metaObject);
        this.setUpdateFieldValByName("updated",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        this.setUpdateFieldValByName("updated",new Date(),metaObject);
    }
}
