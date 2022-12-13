package com.sachinlakshitha.springboot3jspauthinterceptor.config;

import com.sachinlakshitha.springboot3jspauthinterceptor.interceptor.SignInInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private SignInInterceptor signInInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(signInInterceptor).addPathPatterns("/**").excludePathPatterns("/resources/**").excludePathPatterns("/webjars/**").excludePathPatterns("/api/**");
    }
}
