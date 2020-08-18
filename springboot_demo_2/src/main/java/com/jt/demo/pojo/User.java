package com.jt.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
//@EntityScan
@TableName("user") //将对象与表关联,如果对象名称和表名一致，则可以省略不写，大小写忽略
public class User {

	@TableId(type = IdType.AUTO)  //定义主键自增
	private Integer id;
	@TableField("name") //表示字段和属性的关联关系，表中字段和属性一致的话可以省略
	private String name;
	private Integer age;
	private String sex;

}
