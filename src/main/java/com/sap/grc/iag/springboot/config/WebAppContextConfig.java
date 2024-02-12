package com.sap.grc.iag.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
//@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = { "com.sap.grc.iag.springboot" })
public class WebAppContextConfig implements WebMvcConfigurer {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {

        // Make environment variables available for Spring's @Value annotation
        return new PropertySourcesPlaceholderConfigurer();
    }
}