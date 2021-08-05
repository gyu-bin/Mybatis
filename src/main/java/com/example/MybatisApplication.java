package com.example;
import javax.servlet.http.HttpSessionListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.example.common.interceptor.SessionListener;
import com.example.common.interceptor.SiteMeshFilter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
    @EnableAspectJAutoProxy(proxyTargetClass = true)
    public class MybatisApplication {

        public static void main(String[] args) {
            SpringApplication.run(MybatisApplication.class, args);
        }
  /*     @Bean
        public InternalResourceViewResolver setupViewResolver() {

            InternalResourceViewResolver resolver = new InternalResourceViewResolver();

            resolver.setPrefix("/WEB-INF/view/");
            resolver.setSuffix(".jsp");
            return resolver;
        }*/
        @Bean
        public FilterRegistrationBean<SiteMeshFilter> siteMeshFilter() {
            FilterRegistrationBean<SiteMeshFilter> filter = new FilterRegistrationBean<SiteMeshFilter>();
            filter.setFilter(new SiteMeshFilter());
            return filter;

        }
        @Bean
        public ServletListenerRegistrationBean<HttpSessionListener> sessionListener() {
            return new ServletListenerRegistrationBean<HttpSessionListener>(new SessionListener());
        }

    }
