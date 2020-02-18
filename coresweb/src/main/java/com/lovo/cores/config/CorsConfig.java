package com.lovo.cores.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类
 */
@Component
public class CorsConfig implements WebMvcConfigurer {
    /**
     * 跨域配置
     * @param registry
     */
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*") //放行所以的方法
                .allowedOrigins("*");//放行所有的域名
    }
}
