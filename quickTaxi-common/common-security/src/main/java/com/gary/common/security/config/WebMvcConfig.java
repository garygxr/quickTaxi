package com.gary.common.security.config;

import com.gary.common.security.interceptor.HeaderInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * webmvc 拦截器配置
 *
 * @author gary
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
    /** 不需要拦截地址 */
    public static final String[] excludeUrls = { "/user/login","/verify-code/generate/**", "/user/logout", "/user/refresh" };

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(getHeaderInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludeUrls)
                .order(-10);
    }

    /**
     * 自定义请求头拦截器
     */
    public HeaderInterceptor getHeaderInterceptor()
    {
        return new HeaderInterceptor();
    }
}