package com.jt.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author huilong
 * @create 2020/7/29 10:58
 */
@Data
@Accessors(chain = true) //生成链式加载结构
public class User {

    private Integer id;
    private String name;
   /* private String username;
    private Integer userId;
    private Integer userid2;*/

}
