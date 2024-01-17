package com.sap.grc.iag.springboot;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class})
public class MainApplication extends SpringBootServletInitializer {

	// @formatter:off
	/*
	 * Active Spring profile set through SPRING_PROFILES_ACTIVE: ${space} in
	 * mta.yaml Filters added and configurations performed by Spring Boot
	 * automatically
	 * 
	 * Configuration: dataSource --> DataSourceConfiguration &
	 * application.properties
	 * 
	 * Filters: dispatcherServlet --> ServletRegistrationBean
	 * springSecurityFilterChain --> WebSecurityConfiguration &
	 * SecurityFilterAutoConfiguration webRequestLoggingFilter -->
	 * FilterRegistrationBean errorPageFilter --> FilterRegistrationBean
	 * characterEncodingFilter --> FilterRegistrationBean
	 *
	 */
	// @formatter:on
}