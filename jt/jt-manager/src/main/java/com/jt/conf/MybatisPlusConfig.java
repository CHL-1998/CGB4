package com.jt.conf;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author huilong
 * @create 2020/7/31 15:46
 */
@Configuration
public class MybatisPlusConfig {

    @Bean //将分页的拦截器交给spring容器管理
    public PaginationInterceptor paginationInterceptor(){

         return new PaginationInterceptor();
    }
}
