package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author huilong
 * @create 2020/8/5 10:38
 * 构建商品详情的pojo对象
 */
@Data
@TableName("tb_item_desc")
@Accessors(chain = true)
public class ItemDesc extends BasePojo{
    @TableId //跟主表id保持一致，所以只标识主键即可
    private Long itemId; //要求与商品表的id保持一致
    private String itemDesc; //商品的详情信息

}
