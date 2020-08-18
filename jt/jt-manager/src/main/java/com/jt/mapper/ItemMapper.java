package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.Item;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ItemMapper extends BaseMapper<Item>{

    @Select("select * from tb_item order by updated desc limit #{startIndex},#{rows}")
    List<Item> findPageObjects(Integer startIndex,Integer rows);


    @Insert("insert into tb_item (image,sell_point,price,num,title,barcode,cid,created,updated) value (#{image},#{sellPoint},#{price},#{num},#{title},#{barcode},#{cid},now(),now())")
    int insertItem(Item item);

    //传递的是两个值，mybatis中需要封装成map传参，默认在每个参数前有注解声明每个参数在map中的key，默认为参数名
    // 在sql语句中根据key取值
    int updateStatus(Integer status,Long[] ids);

    //mybatis单值传参，多值会封装为一个map集合，此处为一个数组，不需要封装成一个map
    //如果在参数前加上注解可以封装成一个map，并可以在注解中声明map中的key，比如此处可以声明为ids
    //
    int deleteItemById(Long ...ids);
}
