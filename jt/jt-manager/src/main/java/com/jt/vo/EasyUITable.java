package com.jt.vo;

import com.jt.pojo.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author huilong
 * @create 2020/7/31 11:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EasyUITable implements Serializable {


    private static final long serialVersionUID = 7127131850565345463L;
    private Long total;
    private List<Item> rows;



}
