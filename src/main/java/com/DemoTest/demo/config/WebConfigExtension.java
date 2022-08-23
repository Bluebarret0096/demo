package com.DemoTest.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfigExtension extends WebMvcConfigurationSupport {

    @Override
    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        antPathMatcher.setCaseSensitive(false);
        pathMatchConfigurer.setPathMatcher(antPathMatcher);
    }

}
