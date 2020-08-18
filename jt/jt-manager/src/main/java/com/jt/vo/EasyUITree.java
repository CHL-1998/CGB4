package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author huilong
 * @create 2020/8/3 11:47
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class EasyUITree implements Serializable {
    //如果对象涉及到了网络传输就必须序列话和反序列化，这里只需要传输字符串，可以不实现序列化接口，加上也可以
    private static final long serialVersionUID = -6430564299899005614L;
    private Long id;  //数据库中的类型位Long
    private String text;
    private String state;
    //private List<EasyUITree> children;
}
