package com.example.asm.configuration;

import com.example.asm.interceptor.AdminLoginInterceptor;
import com.example.asm.interceptor.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private UserLoginInterceptor loginInterceptor;

    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/product/**")
                .addPathPatterns("/hoa-don/**")
                .addPathPatterns("/gio-hang/**")
                .excludePathPatterns("/login");

        registry.addInterceptor(adminLoginInterceptor)
                .addPathPatterns("/user/**")
                .addPathPatterns("/loai-sp/**")
                .addPathPatterns("/san-pham/**")
                .excludePathPatterns("/login");
    }

}
