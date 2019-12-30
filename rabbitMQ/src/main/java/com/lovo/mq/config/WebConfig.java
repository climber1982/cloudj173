package com.lovo.mq.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Component
public class WebConfig implements WebMvcConfigurer {
    /**
     * 放行的静态资源
     * @param registry
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/page/**").addResourceLocations("classpath:/page/");
    }
}
