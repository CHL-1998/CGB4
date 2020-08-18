package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.ItemCat;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author huilong
 * @create 2020/7/31 17:26
 */
public interface ItemCatMapper extends BaseMapper<ItemCat> {

    @Select("select * from tb_item_cat where id=#{id}")
    ItemCat findCat(Integer id);

    @Select("select * from tb_item_cat where parent_id = #{parentId}")
    List<ItemCat> findParentCat(Long parentId);


}
