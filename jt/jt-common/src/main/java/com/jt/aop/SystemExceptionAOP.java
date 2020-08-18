package com.jt.aop;

import com.jt.vo.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author huilong
 * @create 2020/8/4 11:58
 */
@RestControllerAdvice//定义异常处理的通知，只拦截controller层抛出的异常，并且返回json串
public class SystemExceptionAOP {

    /*@ExceptionHandler(RuntimeException.class)
    public Object fail(Exception e){
        e.printStackTrace();
        return JsonResult.fail();
    }*/


    /**
     *   @ExceptionHandler   描述的方法为异常处理方法
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public JsonResult doHandlerRuntimeException(RuntimeException e){

        e.printStackTrace();
        return new JsonResult(e);

    }
}
