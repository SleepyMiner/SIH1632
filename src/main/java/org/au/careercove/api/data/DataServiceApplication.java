package org.au.careercove.api.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAutoConfiguration
@EnableAsync
@ComponentScan(basePackages = { "org.au.careercove.api.data" })
public class DataServiceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DataServiceApplication.class, args);
	}
}
