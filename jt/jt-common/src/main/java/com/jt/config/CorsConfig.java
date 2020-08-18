package com.jt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author huilong
 * @create 2020/8/18 10:50
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    //扩展跨域请求的方法
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        //允许什么样的请求进行跨域访问
        // /*只允许一级目录请求  /**表示多级目录请求
        registry.addMapping("/**")
                .allowedOrigins("http://www.jt.com")
                //是否允许携带cookie
                .allowCredentials(true)
                // 定义探针检测时间
                .maxAge(30);
    }
}
