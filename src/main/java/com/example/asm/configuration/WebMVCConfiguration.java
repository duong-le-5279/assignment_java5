package com.example.asm.configuration;

import com.example.asm.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;

@Configuration
public class WebMVCConfiguration implements WebMvcConfigurer {
    @Bean("messageSource")
    public MessageSource loadMessageSource(){
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:message/validate");
        source.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return source;
    }
//
//    @Autowired
//    private LoginInterceptor loginInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/admin/**")
//                .excludePathPatterns("/login");
//    }

}
