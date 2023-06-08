package com.example.asm.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;

@Configuration
public class WebMVCConfiguration {
    @Bean("messageSource")
    public MessageSource loadMessageSource(){
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:message/validate");
        source.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return source;
    }
}
