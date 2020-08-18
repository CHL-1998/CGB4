package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author huilong
 * @create 2020/7/31 16:22
 */
@Data
@TableName("tb_item_cat")
@Accessors(chain = true)
public class ItemCat extends BasePojo{
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("parent_id")
    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;
    private Boolean isParent;
}
