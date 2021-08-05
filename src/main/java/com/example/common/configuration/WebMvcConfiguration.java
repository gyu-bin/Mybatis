package com.example.common.configuration;

import com.example.common.interceptor.LoggerInterceptor;
import com.example.common.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginInterceptor()) //로그인 인터셉터
                .addPathPatterns("/*") //add= 적용할 url
                .addPathPatterns("/*/*.html")
                .excludePathPatterns("/*logout*") //exclude=제외할url
                .excludePathPatterns("/*login*");

        registry.addInterceptor(new LoggerInterceptor());

        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Bean
    public Filter characterEncodingFilter(){
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return characterEncodingFilter;
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter(){
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }
}
