package com.sap.grc.iag.springboot.config;

import static org.springframework.http.HttpMethod.GET;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.sap.grc.iag.springboot.controller.CustomController;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

        // Make public resource accessible by "anybody"
        httpSecurity.authorizeRequests().antMatchers(GET, "/index.html", "/").permitAll()
                .antMatchers(GET, "/health", "/").permitAll() // health check on CF
                .antMatchers(CustomController.PATH + "/**").permitAll();//

        // [org.springframework.security.web.csrf.CsrfFilter] [] - invalid CSRF token
        // https://iag-acert-qa-qamt.cfapps.sap.hana.ondemand.com/accesscertification.svc/$batch
        httpSecurity.csrf().disable();

        return httpSecurity.build();
    }
}