package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author huilong
 * @create 2020/8/3 20:35
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult {
    private Integer status=200;
    private String msg="ok";
    private Object data;

    public static JsonResult fail(){
        return new JsonResult(201,"业务执行失败",null);
    }

    public static JsonResult success(){
        return new JsonResult(200,"业务执行成功",null);
    }
    public static JsonResult success(Object data){
        return new JsonResult(200,"业务执行成功",data);
    }
    public static JsonResult success(String msg,Object data){
        return new JsonResult(200,msg,data);
    }

    public JsonResult(Object data) {
        this.data = data;
    }

    public JsonResult(String msg) {
        this.msg = msg;
    }

    public JsonResult(Throwable t){
        this.status = 201;
        this.msg= t.getMessage();
    }
}
